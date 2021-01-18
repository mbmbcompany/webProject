package sk.bielik.webProject.entity;

import sk.bielik.webProject.entityDto.TrolleyDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Credentials credentials;

    private double priceWithoutVAT;

    private double priceWithVAT;
//    @OneToOne(fetch = FetchType.EAGER)
//    private Trolley trolley;
    @OneToMany(fetch = FetchType.EAGER)
    private List<TrolleyItem> trolleyItems=new ArrayList<>();

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public double getPriceWithoutVAT() {
        return priceWithoutVAT;
    }

    public void setPriceWithoutVAT(double priceWithoutVAT) {
        this.priceWithoutVAT = priceWithoutVAT;
    }

    public double getPriceWithVAT() {
        return priceWithVAT;
    }

    public void setPriceWithVAT(double priceWithVAT) {
        this.priceWithVAT = priceWithVAT;
    }
//
//    public Trolley getTrolley() {
//        return trolley;
//    }
//
//    public void setTrolley(Trolley trolley) {
//        this.trolley = trolley;
//    }

    public List<TrolleyItem> getTrolleyItems() {
        return trolleyItems;
    }

    public void setTrolleyItems(List<TrolleyItem> trolleyItems) {
        this.trolleyItems = trolleyItems;
    }
}
