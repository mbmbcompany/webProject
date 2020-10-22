package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TrolleyDto {

    long id;

    private List<Product> product =new ArrayList<>();

    private Customer customer;


    public TrolleyDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
