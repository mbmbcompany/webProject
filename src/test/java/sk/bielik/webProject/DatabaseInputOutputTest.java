package sk.bielik.webProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sk.bielik.webProject.entity.Credentials;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.enums.OrderStage;
import sk.bielik.webProject.entity.enums.ProductGroup;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.service.CustomerMapperImpl;
import sk.bielik.webProject.service.serviceImpl.BuyingOrderServiceImpl;
import sk.bielik.webProject.service.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import sk.bielik.webProject.service.serviceImpl.ProductServiceImpl;
import sk.bielik.webProject.service.serviceImpl.TrolleyServiceImpl;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;


@SpringBootTest
public class DatabaseInputOutputTest {

    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    TrolleyServiceImpl trolleyService;
    @Autowired
    BuyingOrderServiceImpl buyingOrderService;
    @Autowired
    CustomerMapperImpl customerMapper;
    @Autowired
    ProductServiceImpl productService;


    @Test
    public void savedCustomerToDbIsSameAsFoundCustomerFromDbById(){
        CustomerDto customerDto=new CustomerDto();
        Credentials credentials=new Credentials();
        customerDto.setCredentials(credentials);
        customerDto.getCredentials().setFirstName("Test");
        customerDto.setNickName("Test");
        customerDto.getCredentials().setEmail("Test");
        TrolleyDto trolleyDto=new TrolleyDto();
        TrolleyItemDto trolleyItemDto=new TrolleyItemDto();
        trolleyItemDto.setAddingTime(Timestamp.from(Instant.now()));
        trolleyItemDto.setNumberOfPieces(23);
        trolleyDto.getTrolleyItems().add(trolleyItemDto);
        customerDto.setTrolley(trolleyDto);
        CustomerDto customerWithoutPasswordDto= customerService.saveCustomer(customerDto);
        CustomerDto customerWithoutPasswordDto1= customerService.getCustomerById(customerWithoutPasswordDto.getId());
        CustomerDto customerWithoutPasswordDto2=customerService.getCustomerByNickName(customerWithoutPasswordDto.getNickName());
        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto1.getId());
        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto2.getId());

        //Online Customers database Test

        Customer customer=customerMapper.mapCustomerDtoToCustomer(customerWithoutPasswordDto);
        customerService.addOnlineCustomer(customer.getId(),customer);
        CustomerDto customerWithoutPasswordDto3=customerService.getOnlineCustomerByStringId(customerWithoutPasswordDto.getId()+"");
        Assertions.assertEquals(customerWithoutPasswordDto.getId(),customerWithoutPasswordDto3.getId());
    }


    @Test
    public void savedBuingOrderToDbIsSameAsFoundBuyingOrderFromDbById(){
        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setPriceWithoutVAT(55.23);
        BuyingOrderDto buyingOrderDto=new BuyingOrderDto();
        buyingOrderDto.setInvoice(invoiceDto);
        buyingOrderDto.setPayment(true);
        buyingOrderDto.setStageOfOrder(OrderStage.ORDERED);
        BuyingOrderDto returnedBuyingOrderDto=buyingOrderService.save(buyingOrderDto);
        BuyingOrderDto foundByIdBuyingOrder=buyingOrderService.findById(returnedBuyingOrderDto.getId());
        Assertions.assertEquals(returnedBuyingOrderDto.getId(),foundByIdBuyingOrder.getId());
    }

    @Test
    public void savedProductToDbIsSameAsFoundProductFromDbById(){
        ProductDto productDto=new ProductDto();
        productDto.setName("Test");
        productDto.setProductGroup(ProductGroup.ELECTRO);
        productDto.setPrice(new BigDecimal(56));
        productDto.setNumberOfPiecesInStock(23);
        productDto.setDescription("Test");
        ProductDto returnedProduct=productService.save(productDto);
        ProductDto foundByIdProduct=productService.getProductDtoById(returnedProduct.getId());
        Assertions.assertEquals(returnedProduct.getId(),foundByIdProduct.getId());
    }
}
