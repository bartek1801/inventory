package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.commands.AddProductCommand;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.transaction.Transactional;

@Component
public class AddProductHandler {

    private ProductRepository productRepository;

    public AddProductHandler(ProductRepository warehouseRepository) {
        this.productRepository = warehouseRepository;
    }

    @Transactional
    public void handle(AddProductCommand command) {
        Product product = productRepository.findById(command.getSkuCode())
                .map(prod -> prod.sumAmount(command.getAmount()))
                .orElseGet(() -> new Product(command.getSkuCode(), command.getAmount()));
        productRepository.save(product);
    }
}
