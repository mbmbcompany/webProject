package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.Customer;

import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;

@Mapper
public interface CustomerMapper {

    CustomerWithoutPasswordDto mapCustomerToCustomerWithoutPasswordDto(Customer customer);

    Customer mapCustomerWithoutPasswordDtoToCustomer(CustomerWithoutPasswordDto customerWithoutPasswordDto);

    CustomerDto mapCustomerToCustomerDto(Customer customer);

    Customer mapCustomerDtoToCustomer(CustomerDto customerDto);

    CustomerWithoutPasswordDto mapCustomerDtoToCustomerWithoutPasswordDto(CustomerDto customerDto);

    CustomerDto mapCustomerWithoutPasswordDtoToCustomerDto(CustomerWithoutPasswordDto customerWithoutPasswordDto);
}
