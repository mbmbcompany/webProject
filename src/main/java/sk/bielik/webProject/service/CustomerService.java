package sk.bielik.webProject.service;

import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entityDto.*;


import java.util.List;

public interface CustomerService {

    CustomerWithoutPasswordDto getCustomerById(Long customerId);
    List<CustomerWithoutPasswordDto> getAllCustomers();
    CustomerWithoutPasswordDto saveCustomer(CustomerDto customerDto);
    CustomerWithoutPasswordDto addOnlineCustomer(long id,Customer customer);
    List<CustomerWithoutPasswordDto> getOnlineCustomers();
    CustomerWithoutPasswordDto getOnlineCustomerByStringId(String id);
    void deleteOnlineCustomerByStringId(String id);
    CustomerWithoutPasswordDto getCustomerByNickName(String userNickName);
    CustomerWithoutPasswordDto addOnlineCustomerWithActiveSessionToOnlineCustomers(String userNickName);
}
