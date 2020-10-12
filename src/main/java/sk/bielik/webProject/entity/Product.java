package sk.bielik.webProject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.bielik.webProject.converter.ProductGroupConverter;
import sk.bielik.webProject.entity.enums.ProductGroup;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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

    private Timestamp addedToTrolley;
    @ManyToMany(mappedBy = "productList")
    private Set<Trolley> trolleyList=new HashSet<>();

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

    public Timestamp getAddedToTrolley() {
        return addedToTrolley;
    }

    public void setAddedToTrolley(Timestamp addedToTrolley) {
        this.addedToTrolley = addedToTrolley;
    }
}
