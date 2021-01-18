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
import sk.bielik.webProject.service.serviceImpl.*;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


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
    @Autowired
    InvoiceServiceImpl invoiceService;

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
    public void savedBuyingOrderToDbIsSameAsFoundBuyingOrderFromDbById(){

        ProductDto productDto=new ProductDto();
        productDto.setName("TestProduct");
        productDto.setProductGroup(ProductGroup.GROCERY);
        productDto.setPrice(new BigDecimal(65));
        productDto.setDescription("TestProduct");
        productDto.setNumberOfPiecesInStock(100);
        ProductDto productDto1=productService.save(productDto);
        TrolleyItemDto trolleyItemDto= new TrolleyItemDto();
        trolleyItemDto.setNumberOfPieces(5);
        trolleyItemDto.setAddingTime(Timestamp.from(Instant.now()));
        trolleyItemDto.setProduct(productDto1);
        List<TrolleyItemDto> trolleyItemDtoList=new ArrayList<>();
        trolleyItemDtoList.add(trolleyItemDto);
        Credentials credentials=new Credentials();
        credentials.setEmail("test@test.sk");
        credentials.setFirstName("Test");
        credentials.setAdress("TestAdress");
        credentials.setBankAccount("testAccount");
        credentials.setSurename("Test");
        credentials.setPhone_number("testNumber");
        TrolleyDto trolleyDto=new TrolleyDto();
        trolleyDto.setTrolleyItems(trolleyItemDtoList);
        CustomerDto customerDto= new CustomerDto();
        customerDto.setCredentials(credentials);
        customerDto.setNickName("Test");
        customerDto.setTrolley(trolleyDto);
        CustomerDto customerDto1=customerService.saveCustomer(customerDto);
        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setPriceWithoutVAT(55.23);
        invoiceDto.setTrolleyItems(customerDto1.getTrolley().getTrolleyItems());
        invoiceDto.setPriceWithVAT(456);
        invoiceDto.setCredentials(credentials);
        BuyingOrderDto buyingOrderDto=new BuyingOrderDto();
        buyingOrderDto.setInvoice(invoiceDto);
        buyingOrderDto.setPayment(false);
        buyingOrderDto.setStageOfOrder(OrderStage.ORDERED);
        BuyingOrderDto returnedBuyingOrderDto=buyingOrderService.save(buyingOrderDto);
        BuyingOrderDto foundByIdBuyingOrder=buyingOrderService.findById(returnedBuyingOrderDto.getId());
        Assertions.assertEquals(returnedBuyingOrderDto.getId(),foundByIdBuyingOrder.getId());
        Assertions.assertEquals(buyingOrderDto.getInvoice().getTrolleyItems().get(buyingOrderDto.getInvoice().getTrolleyItems().size()-1).getId(),returnedBuyingOrderDto.getInvoice().getTrolleyItems().get(returnedBuyingOrderDto.getInvoice().getTrolleyItems().size()-1).getId());
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
