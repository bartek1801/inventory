package pl.com.bottega.inventory.domain.commands;

import org.assertj.core.data.MapEntry;

import java.util.Map;

public class PurchaseCommand implements Validatable {


    private Map<String, Integer> products;


    public PurchaseCommand(Map<String, Integer> products) {
        this.products = products;
    }

    public PurchaseCommand() {
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    @Override
    public void validate(ValidationErrors errors) {
        if (products == null || products.isEmpty())
            errors.add("skus", "are required");
        else {
            for (Map.Entry<String, Integer> product : products.entrySet()) {
                if (product.getValue() < 1 || product.getValue() > 999)
                    errors.add(product.getKey(), "must be between 1 and 999");
            }
        }
    }
}
