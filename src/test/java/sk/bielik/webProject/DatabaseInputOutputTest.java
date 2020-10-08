package sk.bielik.webProject;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.service.CustomerMapperImpl;
import sk.bielik.webProject.service.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;


@SpringBootTest
public class DatabaseInputOutputTest {
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    CustomerMapperImpl customerMapper;


    @Test
    public void savedCustomerToDbIsSameAsFoundCustomerFromDbById(){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setName("Test");
        customerDto.setNickName("Test");
        customerDto.setEmail("Test");
       CustomerWithoutPasswordDto customerWithoutPasswordDto= customerService.saveCustomer(customerDto);

       CustomerWithoutPasswordDto customerWithoutPasswordDto1= customerService.getCustomerById(customerWithoutPasswordDto.getId());
       CustomerWithoutPasswordDto customerWithoutPasswordDto2=customerService.getCustomerByNickName(customerWithoutPasswordDto.getNickName());

        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto1.getId());
        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto2.getId());

        //Online Customers database Test

        Customer customer=customerMapper.mapCustomerWithoutPasswordDtoToCustomer(customerWithoutPasswordDto);
        customerService.addOnlineCustomer(customer.getId(),customer);
        CustomerWithoutPasswordDto customerWithoutPasswordDto3=customerService.getOnlineCustomerByStringId(customerWithoutPasswordDto.getId()+"");
        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto3.getId());
    }
}
