package pl.com.bottega.inventory.api;

import java.util.Map;

public class PurchaseCompleteDto extends PurchaseDto {

    private Map<String, Integer> purchasedProducts ;

    public PurchaseCompleteDto(boolean success, Map<String, Integer> purchasedProducts) {
        super(success);
        this.purchasedProducts = purchasedProducts;
    }

    public PurchaseCompleteDto(boolean success) {
        super(success);
    }

    public Map<String, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<String, Integer> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
