package pl.com.bottega.inventory.api;

import java.util.HashMap;
import java.util.Map;

public class PurchaseSummaryDto {

    private boolean success;

    private Map<String, Integer> purchasedProducts = new HashMap<>();

    private Map<String, Integer> missingProducts = new HashMap<>();

    public PurchaseSummaryDto(boolean success, Map<String, Integer> products) {
        this.success = success;
        if (success)
            this.purchasedProducts = products;
        else
            this.missingProducts = products;

    }

    public PurchaseSummaryDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Integer> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<String, Integer> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public Map<String, Integer> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(Map<String, Integer> missingProducts) {
        this.missingProducts = missingProducts;
    }
}
