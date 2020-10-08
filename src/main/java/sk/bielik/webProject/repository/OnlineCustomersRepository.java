package sk.bielik.webProject.repository;

import sk.bielik.webProject.entity.Customer;

import java.util.List;

public interface OnlineCustomersRepository {

    void addOnlineCustomer(String key, Customer value);
    List<Customer> getOnlineCustomers();
    Customer getOnlineCustomerByStringId(String id);
    void deleteOnlineCustomer(String id);

}
