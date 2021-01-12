package sk.bielik.webProject.entity;

import javax.persistence.*;
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
    @OneToOne(fetch = FetchType.EAGER)
    private Trolley trolley;

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

    public Trolley getTrolley() {
        return trolley;
    }

    public void setTrolley(Trolley trolley) {
        this.trolley = trolley;
    }
}
