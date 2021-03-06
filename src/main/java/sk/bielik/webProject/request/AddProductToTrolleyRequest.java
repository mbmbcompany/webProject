package sk.bielik.webProject.request;

import com.sun.istack.NotNull;

public class AddProductToTrolleyRequest {

    @NotNull
    private long productId;
    @NotNull
    private long numberOfProducts;

    public AddProductToTrolleyRequest() {
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

}
