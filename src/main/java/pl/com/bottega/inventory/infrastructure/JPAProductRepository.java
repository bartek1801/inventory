package pl.com.bottega.inventory.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Component
public class JPAProductRepository implements ProductRepository {

    private EntityManager entityManager;

    public JPAProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getById(String skuCode) {
        return entityManager.find(Product.class, skuCode);
    }

    @Override
    public Optional<Product> findById(String skuCode) {
        return Optional.ofNullable(entityManager.find(Product.class, skuCode));
    }
}
