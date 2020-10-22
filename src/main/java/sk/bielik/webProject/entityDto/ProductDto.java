package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.converter.ProductGroupConverter;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.enums.ProductGroup;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDto {

    private long id;

    private String name;

    private BigDecimal price;

    private long numberOfPiecesInStock;

    private String description;

    private ProductGroup productGroup;

//    private Timestamp addedToTrolley;
//
//    private long numberOfPiecesInTrolley;
//
//    private List<Trolley> trolley;

    public ProductDto() {
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

//    public List<Trolley> getTrolley() {
//        return trolley;
//    }
//
//    public void setTrolley(List<Trolley> trolley) {
//        this.trolley = trolley;
//    }
//
//    public Timestamp getAddedToTrolley() {
//        return addedToTrolley;
//    }
//
//    public void setAddedToTrolley(Timestamp addedToTrolley) {
//        this.addedToTrolley = addedToTrolley;
//    }
//
//    public long getNumberOfPiecesInTrolley() {
//        return numberOfPiecesInTrolley;
//    }
//
//    public void setNumberOfPiecesInTrolley(long numberOfPiecesInTrolley) {
//        this.numberOfPiecesInTrolley = numberOfPiecesInTrolley;
//    }
}
