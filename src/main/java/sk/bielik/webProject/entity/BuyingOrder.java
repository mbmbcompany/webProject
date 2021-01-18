package sk.bielik.webProject.entity;

import sk.bielik.webProject.entity.enums.OrderStage;

import javax.persistence.*;

@Entity
public class BuyingOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean payment;
    @OneToOne(cascade = CascadeType.ALL)
    private Invoice invoice;
    @Enumerated(EnumType.STRING)
    private OrderStage stageOfOrder;

    public BuyingOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public OrderStage getStageOfOrder() {
        return stageOfOrder;
    }

    public void setStageOfOrder(OrderStage stageOfOrder) {
        this.stageOfOrder = stageOfOrder;
    }
}
