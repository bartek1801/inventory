package pl.com.bottega.inventory.domain.repositories;

import pl.com.bottega.inventory.domain.Product;

import java.util.List;

public interface ProductRepository {

    void save (Product product);

    List<Product> getBySkuCode(String skuCode);

    Product findById(String skuCode);


}
