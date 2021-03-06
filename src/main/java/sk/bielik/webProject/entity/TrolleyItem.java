package sk.bielik.webProject.entity;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
public class TrolleyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Product product;

    private long numberOfPieces;

    private Timestamp addingTime;

    public TrolleyItem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(long numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }

    public Timestamp getAddingTime() {
        return addingTime;
    }

    public void setAddingTime(Timestamp addingTime) {
        this.addingTime = addingTime;
    }

}
