package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.ProductDto;
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
    public CustomerWithoutPasswordDto getCustomerById(Long customerId) {
        return customerMapper.mapCustomerToCustomerWithoutPasswordDto(customerRepository.getCustomerById(customerId));
    }

    @Override
    public List<CustomerWithoutPasswordDto> getAllCustomers() {
        return customerRepository.getAllCustomers()
                .stream().map(customer -> customerMapper.mapCustomerToCustomerWithoutPasswordDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerWithoutPasswordDto saveCustomer(CustomerDto customerDto) {
        return customerMapper.
                mapCustomerToCustomerWithoutPasswordDto
                        (customerRepository.saveCustomer
                                (customerMapper.mapCustomerDtoToCustomer(customerDto)));
    }

    @Override
    public CustomerWithoutPasswordDto addOnlineCustomer(long id, Customer customer) {
        session.setAttribute("userNickName",customer.getNickName());
        onlineCustomersRepository.addOnlineCustomer(""+id,customer);
        CustomerWithoutPasswordDto customerWithoutPasswordDto=
                customerMapper.mapCustomerToCustomerWithoutPasswordDto
                        (onlineCustomersRepository.
                                getOnlineCustomerByStringId(""+id));
        return customerWithoutPasswordDto;
    }

    public List<CustomerWithoutPasswordDto> getOnlineCustomers() {
           return onlineCustomersRepository.getOnlineCustomers().
                   stream().map(customer -> customerMapper.mapCustomerToCustomerWithoutPasswordDto(customer)).
                   collect(Collectors.toList());
    }

    @Override
    public CustomerWithoutPasswordDto getOnlineCustomerByStringId(String id) {
        return customerMapper.mapCustomerToCustomerWithoutPasswordDto(onlineCustomersRepository.getOnlineCustomerByStringId(id));
    }

    @Override
    public void deleteOnlineCustomerByStringId(String id) {
        onlineCustomersRepository.deleteOnlineCustomer(id);
    }

    @Override
    public CustomerWithoutPasswordDto getCustomerByNickName(String userNickName) {
        return customerMapper.mapCustomerToCustomerWithoutPasswordDto(customerRepository.getCustomerByNickName(userNickName));
    }

    @Override
    public CustomerWithoutPasswordDto addOnlineCustomerWithActiveSessionToOnlineCustomers(String userNickName) {
      Customer customer= customerRepository.getCustomerByNickName(userNickName);
      onlineCustomersRepository.addOnlineCustomer(customer.getId()+"",customer);
        return customerMapper.
                mapCustomerToCustomerWithoutPasswordDto(onlineCustomersRepository
                        .getOnlineCustomerByStringId(customer.getId()+""));
    }

}
