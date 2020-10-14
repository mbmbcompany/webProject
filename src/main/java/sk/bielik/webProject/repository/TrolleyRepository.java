package sk.bielik.webProject.repository;

import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TrolleyRepository {
    Customer addProductToTrolley(Customer customer, Product product);
    void deleteProductFromTrolley(long productId,long numberOfProducts);
    List<Product> buyProducts();
    List<Product> getAllProductsFromTrolley();
}
