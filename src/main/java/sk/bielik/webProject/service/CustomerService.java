package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.*;


import java.util.List;

public interface CustomerService {

    CustomerWithoutPasswordDto getCustomerById(Long customerId);
    List<CustomerWithoutPasswordDto> getAllCustomers();
    CustomerWithoutPasswordDto saveCustomer(CustomerDto customerDto);
}
