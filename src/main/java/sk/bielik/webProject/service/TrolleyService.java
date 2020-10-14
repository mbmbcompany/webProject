package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TrolleyService {

    AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest, HttpServletRequest request, HttpServletResponse response);
    void deleteProductFromTrolley(long productId,long numberOfProducts);
    List<ProductBasicInfoDto> buyProducts();
    List<ProductBasicInfoDto> getAllProductsFromTrolley();
}
