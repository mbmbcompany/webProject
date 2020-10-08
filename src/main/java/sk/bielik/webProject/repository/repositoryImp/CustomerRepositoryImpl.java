package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.*;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.repository.CustomerRepository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl {

    private final CustomerRepository customerRepository;

    public CustomerRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(long customerId){
        return customerRepository.findById(customerId).get();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerByNickName(String nickName) {
        return customerRepository.findByNickName(nickName);
    }
}
