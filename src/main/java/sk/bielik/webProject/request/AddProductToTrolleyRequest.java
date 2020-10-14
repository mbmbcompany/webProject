package sk.bielik.webProject.request;

import com.sun.istack.NotNull;

public class AddProductToTrolleyRequest {
    @NotNull
    private long customerId;
    @NotNull
    private long productId;
    @NotNull
    private long numberOfProducts;

    public AddProductToTrolleyRequest() {
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(long numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    @Override
    public String toString() {
        return "AddProductToTrolleyRequest{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                ", numberOfProducts=" + numberOfProducts +
                '}';
    }
}
