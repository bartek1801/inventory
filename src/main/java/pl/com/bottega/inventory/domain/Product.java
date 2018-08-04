package pl.com.bottega.inventory.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private String skuCode;

    private Integer amount;

    public Product() {
    }

    public Product(String skuCode, Integer amount) {
        this.skuCode = skuCode;
        this.amount = amount;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void sustractAmount(Integer value) {
        this.amount -= value;
    }

    public Product sumAmount(Integer value) {
        this.amount += value;
        return this;
    }
}
