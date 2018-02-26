package pl.com.bottega.inventory.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.repositories.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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
    public List<Product> getBySkuCode(String skuCode) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.skuCode = :skuCode");
        query.setParameter("skuCode", skuCode);
        return (List<Product>) query.getResultList();
    }

    @Override
    public Product findById(String skuCode) {
        return entityManager.find(Product.class, skuCode);
    }
}
