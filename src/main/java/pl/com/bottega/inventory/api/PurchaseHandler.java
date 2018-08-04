package pl.com.bottega.inventory.api;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.commands.InvalidCommandException;
import pl.com.bottega.inventory.domain.commands.PurchaseCommand;
import pl.com.bottega.inventory.domain.commands.Validatable;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
        return productsFromRepo.stream()
                .filter(product -> product.getAmount() < products.get(product.getSkuCode()))
                .map(prFromRepo -> Tuple.of(prFromRepo.getSkuCode(), products.get(prFromRepo.getSkuCode())))
                .collect(Collectors.toMap(Tuple2::_1, Tuple2::_2));
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
