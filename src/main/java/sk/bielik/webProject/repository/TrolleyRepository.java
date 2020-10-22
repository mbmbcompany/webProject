package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface TrolleyRepository extends JpaRepository<Trolley,Long> {
    Customer addProductToTrolley(Customer customer, Product product,long numberOfProducts);
    void deleteProductFromTrolley(Customer customer, TrolleyItem trolleyItem);
    boolean buyProducts(Customer customer);
    List<TrolleyItem> getAllProductsFromTrolley(Customer customer);
    Trolley addTrolleyToDatabase(Trolley trolley);
}
