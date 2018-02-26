package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.commands.AddProductCommand;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class AddProductHandler {

    private ProductRepository productRepository;

    public AddProductHandler(ProductRepository warehouseRepository) {
        this.productRepository = warehouseRepository;
    }

    @Transactional
    public void handle(AddProductCommand command) {
        Product product = productRepository.findById(command.getSkuCode());
        if (product == null) {
            Product newProduct = new Product(command.getSkuCode(), command.getAmount());
            productRepository.save(newProduct);
        } else {
            product.sumAmount(command.getAmount());
            productRepository.save(product);
        }
    }
}
