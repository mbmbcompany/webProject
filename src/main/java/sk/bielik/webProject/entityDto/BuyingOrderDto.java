package sk.bielik.webProject.entityDto;

import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Invoice;
import sk.bielik.webProject.entity.enums.OrderStage;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

public class BuyingOrderDto {

    private long id;

    private boolean payment;

    private InvoiceDto invoice;

    private OrderStage stageOfOrder;

    public BuyingOrderDto() {
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

    public InvoiceDto  getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceDto  invoice) {
        this.invoice = invoice;
    }

    public OrderStage getStageOfOrder() {
        return stageOfOrder;
    }

    public void setStageOfOrder(OrderStage stageOfOrder) {
        this.stageOfOrder = stageOfOrder;
    }
}
