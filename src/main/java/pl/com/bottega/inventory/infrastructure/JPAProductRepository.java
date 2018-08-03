package pl.com.bottega.inventory.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.persistence.EntityManager;

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
    public Product findById(String skuCode) {
        return entityManager.find(Product.class, skuCode);
    }
}
