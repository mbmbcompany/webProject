package sk.bielik.webProject.service;

import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entityDto.*;


import java.util.List;

public interface CustomerService {

    CustomerDto getCustomerById(Long customerId);
    List<CustomerWithoutPasswordDto> getAllCustomers();
    CustomerDto saveCustomer(CustomerDto customerDto);
    CustomerDto addOnlineCustomer(long id, Customer customer);
    List<CustomerWithoutPasswordDto> getOnlineCustomers();
    CustomerDto getOnlineCustomerByStringId(String id);
    void deleteOnlineCustomerByStringId(String id);
    CustomerDto getCustomerByNickName(String userNickName);
    CustomerDto addOnlineCustomerWithActiveSessionToOnlineCustomers(String userNickName);
}
