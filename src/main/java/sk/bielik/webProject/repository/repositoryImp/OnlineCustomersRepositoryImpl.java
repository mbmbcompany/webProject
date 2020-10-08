package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.database.OnlineCustomers;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.repository.OnlineCustomersRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OnlineCustomersRepositoryImpl implements OnlineCustomersRepository {

    OnlineCustomers onlineCustomers= OnlineCustomers.createOrGetOnlineUsers();

    @Override
    public void addOnlineCustomer(String key, Customer value) {
      onlineCustomers.getMap().put(key,value);
    }

    @Override
    public List<Customer> getOnlineCustomers() {
        return  new ArrayList<Customer>(onlineCustomers.getMap().values());
                //onlineCustomers.getMap().values().stream().collect(Collectors.toList());
    }

    @Override
    public Customer getOnlineCustomerByStringId(String key) {
        return  onlineCustomers.getMap().get(key);
    }

    @Override
    public void deleteOnlineCustomer(String id) {
        onlineCustomers.getMap().remove(id);
    }
}
