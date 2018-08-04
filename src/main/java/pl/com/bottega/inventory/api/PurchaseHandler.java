package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.commands.InvalidCommandException;
import pl.com.bottega.inventory.domain.commands.PurchaseCommand;
import pl.com.bottega.inventory.domain.commands.Validatable;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PurchaseHandler {

    private ProductRepository productRepository;

    public PurchaseHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public PurchaseDto handle(PurchaseCommand command) {
        Map<String, Integer> products = command.getProducts();
        List<Product> productsFromRepo = productsFoundsInRepo(command.getProducts().keySet());
        checkSkuCodesCorrectness(products, productsFromRepo);
        Map<String, Integer> incorrectProducts = getProductsWithIncorrectAmount(products, productsFromRepo);
        if (areIncorrectProducts(incorrectProducts)) {
            return new PurchaseNotValidDto(false, incorrectProducts);
        }
        saveProducts(products);
        return new PurchaseCompleteDto(true, products);
    }

    private List<Product> productsFoundsInRepo(Set<String> skuCodes) {
        return skuCodes.stream()
                .map(skuCode -> productRepository.findById(skuCode))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private boolean areIncorrectProducts(Map<String, Integer> incorrectProducts) {
        return incorrectProducts.size() != 0;
    }


    private void saveProducts(Map<String, Integer> products) {
        for (Map.Entry<String, Integer> item : products.entrySet()) {
            Product product = productRepository.getById(item.getKey());
            product.sustractAmount(item.getValue());
            productRepository.save(product);
        }
    }

    private Map<String, Integer> getProductsWithIncorrectAmount(Map<String, Integer> products, List<Product> productsFromRepo) {
        Map<String, Integer> incorrectProducts = new HashMap<>();
//
//        productsFromRepo.stream()
//                .filter(product -> product.getAmount() < products.get(product.getSkuCode()))
//                .collect(Collectors.toMap(Product::getSkuCode, products.get());
        //TODO przerobić na mape gdze kluczem jest skuCode a wartoscia zly amount

        for (Map.Entry<String, Integer> item : products.entrySet()) {
            Product product = productRepository.getById(item.getKey());
            if (item.getValue() > product.getAmount())
                incorrectProducts.put(item.getKey(), item.getValue());
        }
        return incorrectProducts;
    }


    private void checkSkuCodesCorrectness(Map<String, Integer> products, List<Product> productsFromRepo) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        products.keySet()
                .forEach(skuCode -> {
                    if (!skuCodeExisting(skuCode, productsFromRepo))
                        errors.add(skuCode, "no such sku");
                });
        if (!errors.isValid())
            throw new InvalidCommandException(errors);
    }

    private boolean skuCodeExisting(String skuCode, List<Product> productsFromRepo) {
        return productsFromRepo.stream()
                .map(Product::getSkuCode)
                .collect(Collectors.toList())
                .contains(skuCode);
    }
}
