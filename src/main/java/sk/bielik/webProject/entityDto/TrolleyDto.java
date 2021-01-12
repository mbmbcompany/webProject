package sk.bielik.webProject.entityDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entity.TrolleyItem;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TrolleyDto {

    long id;
    private  List<TrolleyItemDto> trolleyItems=new ArrayList<>();

    public TrolleyDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TrolleyItemDto> getTrolleyItems() {
        return trolleyItems;
    }

    public void setTrolleyItems(List<TrolleyItemDto> trolleyItems) {
        this.trolleyItems = trolleyItems;
    }
}
