package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.enums.ProductGroup;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class ProductDto {

    private long id;

    private String title;

    private BigDecimal price;

    private long numberOfPiecesInStock;

    private String description;

    private ProductGroup productGroup;

    private Timestamp addedToTrolley;

    private Set<Trolley> trolleyList=new HashSet<>();

    public ProductDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<Trolley> getTrolleyList() {
        return trolleyList;
    }

    public void setTrolleyList(Set<Trolley> trolleyList) {
        this.trolleyList = trolleyList;
    }
}
