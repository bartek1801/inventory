package pl.com.bottega.inventory.domain.repositories;

import pl.com.bottega.inventory.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    void save (Product product);

    Product getById(String skuCode);

    Optional<Product> findById(String skuCode);


}
