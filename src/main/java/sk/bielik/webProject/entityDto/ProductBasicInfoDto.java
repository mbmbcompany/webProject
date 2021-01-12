package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.converter.ProductGroupConverter;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.enums.ProductGroup;

import javax.persistence.Convert;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class ProductBasicInfoDto {

    private long id;

    private String name;

    private BigDecimal price;

    private long numberOfPiecesInStock;

    private String description;

    private ProductGroup productGroup;

    public ProductBasicInfoDto() {
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
