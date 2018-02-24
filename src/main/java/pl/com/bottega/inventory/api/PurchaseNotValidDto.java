package pl.com.bottega.inventory.api;

import java.util.HashMap;
import java.util.Map;

public class PurchaseNotValidDto extends PurchaseAbstractDto {

    private Map<String, Integer> missingProducts ;

    public PurchaseNotValidDto(boolean success, Map<String, Integer> purchasedProducts) {
        super(success);
        this.missingProducts = purchasedProducts;
    }

    public PurchaseNotValidDto(boolean success) {
        super(success);
    }

    public Map<String, Integer> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(Map<String, Integer> missingProducts) {
        this.missingProducts = missingProducts;
    }
}
