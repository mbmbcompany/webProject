package sk.bielik.webProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trolley {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne(mappedBy ="trolley")
    @JsonBackReference
    private Customer customer;
    @OneToMany(mappedBy = "trolley",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private  List<TrolleyItem> trolleyItems=new ArrayList<>();


//toto zmenit na ManyToMany a do pomocnej tabulky pridat stlpce ako cas pridania a pocet kusov
//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JsonBackReference
//    private List<Product> product =new ArrayList<>();

    public Trolley() {
    }
//
//    public List<Product> getProduct() {
//        return product;
//    }
//
//    public void setProduct(List<Product> product) {
//        this.product = product;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TrolleyItem> getTrolleyItems() {
        return trolleyItems;
    }

    public void setTrolleyItems(List<TrolleyItem> trolleyItems) {
        this.trolleyItems = trolleyItems;
    }
}
