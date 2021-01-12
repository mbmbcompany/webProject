package sk.bielik.webProject.request;

import org.springframework.lang.Nullable;

public class UpdateOrderedBuyingOrderRequest {

    private long buyingOrderId;
    @Nullable
    private boolean payment=false;
    @Nullable
    private String buyingOrderStage;

    public UpdateOrderedBuyingOrderRequest() {
    }

    public UpdateOrderedBuyingOrderRequest(long buyingOrderId, boolean payment, String buyingOrderStage) {
        this.buyingOrderId = buyingOrderId;
        this.payment = payment;
        this.buyingOrderStage = buyingOrderStage;
    }

    public long getBuyingOrderId() {
        return buyingOrderId;
    }

    public void setBuyingOrderId(long buyingOrderId) {
        this.buyingOrderId = buyingOrderId;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public String getBuyingOrderStage() {
        return buyingOrderStage;
    }

    public void setBuyingOrderStage(String buyingOrderStage) {
        this.buyingOrderStage = buyingOrderStage;
    }
}
