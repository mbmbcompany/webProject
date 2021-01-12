package sk.bielik.webProject.entityDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entity.Trolley;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

public class TrolleyItemDto {

    private long id;

    private ProductDto product;

    private long numberOfPieces;

    private Timestamp addingTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
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
