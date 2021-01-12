package sk.bielik.webProject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trolley {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private  List<TrolleyItem> trolleyItems=new ArrayList<>();

    public Trolley() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TrolleyItem> getTrolleyItems() {
        return trolleyItems;
    }

    public void setTrolleyItems(List<TrolleyItem> trolleyItems) {
        this.trolleyItems = trolleyItems;
    }
}
