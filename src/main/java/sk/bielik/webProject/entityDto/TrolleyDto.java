package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.entity.Product;

import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

public class TrolleyDto {

    long id;

    private Set<Product> productList=new HashSet<>();

    public TrolleyDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Product> getProductList() {
        return productList;
    }

    public void setProductList(Set<Product> productList) {
        this.productList = productList;
    }
}
