package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.repository.repositoryImp.CustomerRepositoryImpl;
import sk.bielik.webProject.service.CustomerMapperImpl;
import sk.bielik.webProject.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapperImpl customerMapper;

    private final CustomerRepositoryImpl customerRepository;

    public CustomerServiceImpl(CustomerMapperImpl customerMapper, CustomerRepositoryImpl customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
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
}
