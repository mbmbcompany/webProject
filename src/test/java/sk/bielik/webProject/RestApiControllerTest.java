package sk.bielik.webProject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import sk.bielik.webProject.entity.Credentials;
import sk.bielik.webProject.entity.enums.OrderStage;
import sk.bielik.webProject.entity.enums.ProductGroup;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.request.RegistrationRequest;
import sk.bielik.webProject.request.SignInRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.serviceImpl.BuyingOrderServiceImpl;
import sk.bielik.webProject.service.serviceImpl.CustomerServiceImpl;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestApiControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    HttpSession session;
    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    BuyingOrderServiceImpl buyingOrderService;

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testCustomerRestRegistrationRestSignInOutRestController() throws Exception {

        CustomerDto customerDto=new CustomerDto();
        Credentials credentials=new Credentials();
        customerDto.setCredentials(credentials);
        customerDto.getCredentials().setFirstName("Test");
        customerDto.setNickName("Test");
        customerDto.getCredentials().setEmail("Test");
        customerDto.setPassword("TestTest");
        customerDto.getCredentials().setAdress("Test");
        customerDto.getCredentials().setPhone_number("Test");
        customerDto.getCredentials().setSurename("Test");
        String passwordVerif="TestTest";
        RegistrationRequest request=new RegistrationRequest(customerDto,passwordVerif);

        String returnedRegistration=mockMvc.perform(post("/registration").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).
                andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(returnedRegistration,"Hello Test ,you have been successfully registered Sending main web page.");

        String returnedCustomers=mockMvc.perform(get("/customers").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
       List<CustomerWithoutPasswordDto> customerWithoutPasswordDtoList= objectMapper.readValue(returnedCustomers, new TypeReference<List<CustomerWithoutPasswordDto>>() {});
       Assertions.assertEquals(customerDto.getCredentials().getPhone_number(),customerWithoutPasswordDtoList.get(customerWithoutPasswordDtoList.size()-1).getCredentials().getPhone_number());

        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setNickName(customerDto.getNickName());
        signInRequest.setPassword(customerDto.getPassword());
        String returnedSignIn=mockMvc.perform(post("/sign/in").
                content(objectMapper.writeValueAsString(signInRequest)).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(returnedSignIn,"Hello Test . You signed in successfully Sending you main webpage.");

        String returnedListOfOnlineUsers=mockMvc.perform(get("/customers/onlineCustomers").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        List<CustomerWithoutPasswordDto> returnedCustomerWithoutPasswordDtoList=objectMapper.readValue(returnedListOfOnlineUsers, new TypeReference<List<CustomerWithoutPasswordDto>>() {});
        Assertions.assertEquals(signInRequest.getNickName(),returnedCustomerWithoutPasswordDtoList.get(returnedCustomerWithoutPasswordDtoList.size()-1).getNickName());

        //addProduct
        ProductDto productDto=new ProductDto();
        productDto.setName("Test");
        productDto.setProductGroup(ProductGroup.ELECTRO);
        productDto.setPrice(new BigDecimal(20));
        productDto.setNumberOfPiecesInStock(40);
        productDto.setDescription("Test");
        String addResult=mockMvc.perform(post("/products/addProduct").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(productDto))).
                andExpect(status().isCreated()).andReturn().getResponse().
                getContentAsString();
        ProductDto returnedProductDto=objectMapper.readValue(addResult,ProductDto.class);
        Assertions.assertEquals(productDto.getName(),returnedProductDto.getName());

    }

    @Test
    public void testAddProductGetProductsUpdateProductDeleteProduct() throws Exception {
        //addProduct
        ProductDto productDto=new ProductDto();
        productDto.setName("Test");
        productDto.setProductGroup(ProductGroup.ELECTRO);
        productDto.setPrice(new BigDecimal(20));
        productDto.setNumberOfPiecesInStock(40);
        productDto.setDescription("Test");
        String addResult=mockMvc.perform(post("/products/addProduct").
                contentType(MediaType.APPLICATION_JSON).
                content(objectMapper.writeValueAsString(productDto))).
                andExpect(status().isCreated()).andReturn().getResponse().
                getContentAsString();
        ProductDto returnedProductDto=objectMapper.readValue(addResult,ProductDto.class);
        Assertions.assertEquals(productDto.getName(),returnedProductDto.getName());
        //getProduct
        String getResult=mockMvc.perform(get("/products").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn().
                getResponse().getContentAsString();
        String getResultWithId=mockMvc.perform(get("/products?id=1").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn().
                getResponse().getContentAsString();
        List<ProductDto> productDtos=objectMapper.readValue(getResult, new TypeReference<List<ProductDto>>() {});
        ProductDto productDto1=objectMapper.readValue(getResultWithId,ProductDto.class);
        Assertions.assertEquals(returnedProductDto.getId(),productDtos.get(0).getId());
        Assertions.assertEquals(productDtos.get(0).getId(),productDto1.getId());
        //updateProduct
        ProductDto productDto2=new ProductDto();
        productDto2.setDescription("Test2");
        productDto2.setNumberOfPiecesInStock(100);
        productDto2.setPrice(new BigDecimal(100));
        productDto2.setProductGroup(ProductGroup.FURNITURE);
        productDto2.setName("Test2");
        String updateResult=mockMvc.perform(MockMvcRequestBuilders.put("/products/updateProduct?id=1").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDto2))).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ProductDto updatedProduct=objectMapper.readValue(updateResult,ProductDto.class);
        Assertions.assertEquals(productDto2.getName(),updatedProduct.getName());
        //deleteProduct
        String deleteProduct=mockMvc.perform(delete("/products/delete/1").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("Product with id:1 was successfully deleted. ",deleteProduct);
    }

    @Test
    public void BuyingOrderRestControllerTest() throws Exception {

        InvoiceDto invoiceDto=new InvoiceDto();
        invoiceDto.setPriceWithoutVAT(55.23);
        BuyingOrderDto buyingOrderDto=new BuyingOrderDto();
        buyingOrderDto.setInvoice(invoiceDto);
        buyingOrderDto.setPayment(true);
        buyingOrderDto.setStageOfOrder(OrderStage.ORDERED);
        BuyingOrderDto returnedBuyingOrderDto= buyingOrderService.save(buyingOrderDto);
        String mockedFindBuyingOrder= mockMvc.perform(get("/buyingOrder/"+returnedBuyingOrderDto.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        BuyingOrderDto mockBuyingOrder=objectMapper.readValue(mockedFindBuyingOrder,BuyingOrderDto.class);
        Assertions.assertEquals(returnedBuyingOrderDto.getId(),mockBuyingOrder.getId());

    }

}
