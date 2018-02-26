package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.commands.InvalidCommandException;
import pl.com.bottega.inventory.domain.commands.PurchaseCommand;
import pl.com.bottega.inventory.domain.commands.Validatable;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class PurchaseHandler {

    private ProductRepository productRepository;

    public PurchaseHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public PurchaseAbstractDto handle(PurchaseCommand command) {
        Map<String, Integer> products = command.getProducts();
        checkSkuCodesCorrectness(products);
        Map<String, Integer> incorrectProducts = getProductsWithIncorrectAmount(products);
        if (areNotIncorrectProducts(incorrectProducts)) {
            saveProducts(products);
            return new PurchaseCompleteDto(true, products);
        } else
            return new PurchaseNotValidDto(false, incorrectProducts);
    }

    private boolean areNotIncorrectProducts(Map<String, Integer> incorrectProducts) {
        return incorrectProducts.size() == 0;
    }


    private void saveProducts(Map<String, Integer> products) {
        Map<String, Integer> savedProducts = new HashMap<>();
        for (Map.Entry<String, Integer> item : products.entrySet()) {
            Product product = productRepository.findById(item.getKey());
            product.sustractAmount(item.getValue());
            productRepository.save(product);
            savedProducts.put(item.getKey(), item.getValue());
        }
    }

    private Map<String, Integer> getProductsWithIncorrectAmount(Map<String, Integer> products) {
        Map<String, Integer> incorrectProducts = new HashMap<>();
        for (Map.Entry<String, Integer> item : products.entrySet()) {
            Product product = productRepository.findById(item.getKey());
            if (item.getValue() > product.getAmount())
                incorrectProducts.put(item.getKey(), item.getValue());
        }
        return incorrectProducts;
    }


    private void checkSkuCodesCorrectness(Map<String, Integer> products) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        for (Map.Entry<String, Integer> product : products.entrySet()) {
            if (checkSkuCodeExisting(product.getKey())) {
                errors.add(product.getKey(), "no such sku");
            }
        }
        if (!errors.isValid())
            throw new InvalidCommandException(errors);
    }

    private boolean checkSkuCodeExisting(String skuCode) {
        return productRepository.findById(skuCode) == null;
    }
}
