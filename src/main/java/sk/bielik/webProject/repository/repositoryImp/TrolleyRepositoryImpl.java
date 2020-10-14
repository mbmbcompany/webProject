package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.repository.CustomerRepository;
import sk.bielik.webProject.repository.TrolleyRepository;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;

import java.util.List;
@Repository
public class TrolleyRepositoryImpl implements TrolleyRepository {

    private final CustomerRepository customerRepository;

    public TrolleyRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addProductToTrolley(Customer customer, Product product) {
        customer.getTrolley().getProductList().add(product);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteProductFromTrolley(long productId, long numberOfProducts) {

    }

    @Override
    public List<Product> buyProducts() {
        return null;
    }

    @Override
    public List<Product> getAllProductsFromTrolley() {
        return null;
    }
}
