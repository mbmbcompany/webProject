package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.repository.repositoryImp.CustomerRepositoryImpl;
import sk.bielik.webProject.repository.repositoryImp.OnlineCustomersRepositoryImpl;
import sk.bielik.webProject.service.CustomerMapperImpl;
import sk.bielik.webProject.service.CustomerService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapperImpl customerMapper;

    private final CustomerRepositoryImpl customerRepository;

    private final HttpSession session;

    private final OnlineCustomersRepositoryImpl onlineCustomersRepository;

    public CustomerServiceImpl(CustomerMapperImpl customerMapper, CustomerRepositoryImpl customerRepository, HttpSession session, OnlineCustomersRepositoryImpl onlineCustomersRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.session = session;
        this.onlineCustomersRepository = onlineCustomersRepository;
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        return customerMapper.mapCustomerToCustomerDto(customerRepository.getCustomerById(customerId));
    }

    @Override
    public List<CustomerWithoutPasswordDto> getAllCustomers() {
        return customerRepository.getAllCustomers()
                .stream().map(customer -> customerMapper.mapCustomerToCustomerWithoutPasswordDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.
                mapCustomerToCustomerDto
                        (customerRepository.saveCustomer
                                (customerMapper.mapCustomerDtoToCustomer(customerDto)));
    }

    @Override
    public CustomerDto addOnlineCustomer(long id, Customer customer) {
        session.setAttribute("userNickName",customer.getNickName());
        onlineCustomersRepository.addOnlineCustomer(""+id,customer);
        CustomerDto customerDto=
                customerMapper.mapCustomerToCustomerDto
                        (onlineCustomersRepository.
                                getOnlineCustomerByStringId(""+id));
        return customerDto;
    }

    public List<CustomerWithoutPasswordDto> getOnlineCustomers() {
           return onlineCustomersRepository.getOnlineCustomers().
                   stream().map(customer -> customerMapper.mapCustomerToCustomerWithoutPasswordDto(customer)).
                   collect(Collectors.toList());
    }

    @Override
    public CustomerDto getOnlineCustomerByStringId(String id) {
        return customerMapper.mapCustomerToCustomerDto(onlineCustomersRepository.getOnlineCustomerByStringId(id));
    }

    @Override
    public void deleteOnlineCustomerByStringId(String id) {
        onlineCustomersRepository.deleteOnlineCustomer(id);
    }

    @Override
    public CustomerDto getCustomerByNickName(String userNickName) {
        return customerMapper.mapCustomerToCustomerDto(customerRepository.getCustomerByNickName(userNickName));
    }

    @Override
    public CustomerDto addOnlineCustomerWithActiveSessionToOnlineCustomers(String userNickName) {
      Customer customer= customerRepository.getCustomerByNickName(userNickName);
      onlineCustomersRepository.addOnlineCustomer(customer.getId()+"",customer);
        return customerMapper.
                mapCustomerToCustomerDto(onlineCustomersRepository
                        .getOnlineCustomerByStringId(customer.getId()+""));
    }

}
