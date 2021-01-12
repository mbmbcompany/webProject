package sk.bielik.webProject.service;

import sk.bielik.webProject.entity.BuyingOrder;
import sk.bielik.webProject.entityDto.BuyingOrderDto;
import sk.bielik.webProject.entityDto.InvoiceDto;
import sk.bielik.webProject.request.UpdateOrderedBuyingOrderRequest;

public interface BuyingOrderService {

    BuyingOrderDto save(BuyingOrderDto buyingOrderDto);

    BuyingOrderDto findById(long id);

    void deleteById(long id);

    BuyingOrderDto updateOrderedBuyingOrder(UpdateOrderedBuyingOrderRequest updateOrderedBuyingOrderRequest);
}
