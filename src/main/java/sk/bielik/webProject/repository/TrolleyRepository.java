package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.TrolleyItem;

import java.util.List;

public interface TrolleyRepository extends JpaRepository<Trolley,Long> {

    Customer addProductToTrolley(Customer customer, Product product,long numberOfProducts);

    void deleteProductFromTrolley(Customer customer, Long trolleyItemId);

    boolean buyProducts(Customer customer);

    List<TrolleyItem> getAllProductsFromTrolley(Customer customer);

    Trolley addTrolleyToDatabase(Trolley trolley);

    void deleteItemFromTrolleyByItemOrder(Customer customer,int item) throws Exception;
}
