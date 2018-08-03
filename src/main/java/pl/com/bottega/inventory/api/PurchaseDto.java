package pl.com.bottega.inventory.api;

public class PurchaseDto {

    private boolean success;

    public PurchaseDto(boolean success) {
        this.success = success;
    }

    public PurchaseDto() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
