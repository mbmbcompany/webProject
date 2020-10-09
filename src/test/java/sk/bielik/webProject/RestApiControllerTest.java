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
import sk.bielik.webProject.entity.enums.ProductGroup;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.ProductDto;
import sk.bielik.webProject.request.RegistrationRequest;
import sk.bielik.webProject.request.SignInRequest;

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

    private final ObjectMapper objectMapper=new ObjectMapper();

    @Test
    public void testCustomerRestRegistrationRestSignInOutRestController() throws Exception {

        CustomerDto customerDto=new CustomerDto();
        customerDto.setName("Test");
        customerDto.setNickName("Test");
        customerDto.setEmail("Test");
        customerDto.setPassword("TestTest");
        customerDto.setAdress("Test");
        customerDto.setPhone_number("Test");
        customerDto.setSurename("Test");
        String passwordVerif="TestTest";
        RegistrationRequest request=new RegistrationRequest(customerDto,passwordVerif);
        String returned=mockMvc.perform(post("/registration").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request))).
                andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(returned,"Hello Test ,you have been successfully registered Sending main web page.");

        String result=mockMvc.perform(get("/customers").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
       List<CustomerWithoutPasswordDto> customerWithoutPasswordDtoList= objectMapper.readValue(result, new TypeReference<List<CustomerWithoutPasswordDto>>() {});
       Assertions.assertEquals(customerDto.getNickName(),customerWithoutPasswordDtoList.get(0).getNickName());

        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setNickName(customerDto.getNickName());
        signInRequest.setPassword(customerDto.getPassword());
        String cameBack=mockMvc.perform(post("/sign/in").
                content(objectMapper.writeValueAsString(signInRequest)).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        Assertions.assertEquals(cameBack,"Hello Test . You signed in successfully Sending you main webpage.");

        String returnedListOfOnlineUsers=mockMvc.perform(get("/customers/onlineCustomers").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        List<CustomerWithoutPasswordDto> customerWithoutPasswordDtoList1=objectMapper.readValue(returnedListOfOnlineUsers, new TypeReference<List<CustomerWithoutPasswordDto>>() {});
        Assertions.assertEquals(signInRequest.getNickName(),customerWithoutPasswordDtoList1.get(0).getNickName());

//        String returnedOutResponse=mockMvc.perform(get("/sign/out").
//                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
//                andReturn().getResponse().getContentAsString();
    }

    @Test
    public void testAddProductGetProductsUpdateProductDeleteProduct() throws Exception {
        //addProduct
        ProductDto productDto=new ProductDto();
        productDto.setTitle("Test");
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
        Assertions.assertEquals(productDto.getTitle(),returnedProductDto.getTitle());
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
        productDto2.setTitle("Test2");
        String updateResult=mockMvc.perform(MockMvcRequestBuilders.put("/products/updateProduct?id=1").
                contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(productDto2))).
                andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        ProductDto updatedProduct=objectMapper.readValue(updateResult,ProductDto.class);
        Assertions.assertEquals(productDto2.getTitle(),updatedProduct.getTitle());
        //deleteProduct
        String deleteProduct=mockMvc.perform(delete("/products/delete/1").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
                andReturn().getResponse().getContentAsString();
        Assertions.assertEquals("Product with id:1 was successfully deleted. ",deleteProduct);
    }
}
