package sk.bielik.webProject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.request.RegistrationRequest;
import sk.bielik.webProject.request.SignInRequest;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}
