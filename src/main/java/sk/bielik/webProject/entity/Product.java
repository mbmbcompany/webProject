package sk.bielik.webProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import sk.bielik.webProject.converter.ProductGroupConverter;
import sk.bielik.webProject.entity.enums.ProductGroup;
import javax.persistence.*;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal price;

    private long numberOfPiecesInStock;

    private String description;

    @Convert(converter = ProductGroupConverter.class)
    private ProductGroup productGroup;

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getNumberOfPiecesInStock() {
        return numberOfPiecesInStock;
    }

    public void setNumberOfPiecesInStock(long numberOfPiecesInStock) {
        this.numberOfPiecesInStock = numberOfPiecesInStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

}
