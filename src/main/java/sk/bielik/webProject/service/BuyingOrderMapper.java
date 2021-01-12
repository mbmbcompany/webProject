package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.BuyingOrder;
import sk.bielik.webProject.entityDto.BuyingOrderDto;

@Mapper
public interface BuyingOrderMapper {

    BuyingOrder mapBuyingOrderDtoToBuyingOrder(BuyingOrderDto buyingOrderDto);

    BuyingOrderDto mapBuyingOrderToBuyingOrderDto(BuyingOrder buyingOrder);
}
