package sk.bielik.webProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import sk.bielik.webProject.entity.BuyingOrder;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.BuyingOrderDto;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.TrolleyDto;
import sk.bielik.webProject.entityDto.TrolleyItemDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TrolleyService {

    AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest, HttpServletResponse response) throws JsonProcessingException;
    void deleteItemFromTrolley(Long trolleyItemId,HttpServletResponse response);
    void deleteItemByItemOrder( int order,HttpServletResponse response) throws Exception;
    BuyingOrderDto buyProducts(HttpServletResponse response);
    List<TrolleyItemDto> getAllProductsFromTrolley();
    TrolleyDto addTrolleyToDatabase(TrolleyDto trolley);
}
