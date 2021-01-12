package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.service.serviceImpl.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CustomerServiceImpl customerService;

    public CustomerRestController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity getAllCustomers(){
        List<CustomerWithoutPasswordDto> customerList=customerService.getAllCustomers();
        return new ResponseEntity(customerList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCustomerById(@PathVariable("id") long id){
        CustomerDto customerWithoutPasswordDto =customerService.getCustomerById(id);
        return new ResponseEntity(customerWithoutPasswordDto,HttpStatus.OK);
    }

    @GetMapping("/onlineCustomers")
    public ResponseEntity getOnlineCustomers(){

        List<CustomerWithoutPasswordDto> customerWithoutPasswordDtoList=customerService.getOnlineCustomers();
        return new ResponseEntity(customerWithoutPasswordDtoList,HttpStatus.OK);

    }
}
