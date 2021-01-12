package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.entity.Credentials;
import sk.bielik.webProject.entity.Trolley;

import javax.persistence.Embedded;
import javax.persistence.OneToOne;

public class InvoiceDto {

    private long id;

    private Credentials credentials;

    private double priceWithoutVAT;

    private double priceWithVAT;

    private TrolleyDto  trolley;

    public InvoiceDto() {
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

    public TrolleyDto  getTrolley() {
        return trolley;
    }

    public void setTrolley(TrolleyDto  trolley) {
        this.trolley = trolley;
    }
}
