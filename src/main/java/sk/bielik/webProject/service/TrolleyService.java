package sk.bielik.webProject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.TrolleyDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TrolleyService {

    AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest, HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException;
    void deleteProductFromTrolley(long productId,long numberOfProducts);
    List<TrolleyItem> buyProducts();
    List<TrolleyItem> getAllProductsFromTrolley(long customerID);
    TrolleyDto addTrolleyToDatabase(TrolleyDto trolley);
}
