package pl.com.bottega.inventory.domain.repositories;

import pl.com.bottega.inventory.domain.Product;

public interface ProductRepository {

    void save (Product product);

    Product findById(String skuCode);


}
