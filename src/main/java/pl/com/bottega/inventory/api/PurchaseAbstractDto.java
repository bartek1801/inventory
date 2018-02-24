package pl.com.bottega.inventory.api;

public class PurchaseAbstractDto {

    private boolean success;

    public PurchaseAbstractDto(boolean success) {
        this.success = success;
    }

    public PurchaseAbstractDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
