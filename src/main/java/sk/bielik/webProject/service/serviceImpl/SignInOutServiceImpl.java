package sk.bielik.webProject.service.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entityDto.CustomerDto;
import sk.bielik.webProject.repository.repositoryImp.CustomerRepositoryImpl;
import sk.bielik.webProject.request.SignInRequest;
import sk.bielik.webProject.response.SignInResponse;
import sk.bielik.webProject.service.SignInOutService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SignInOutServiceImpl implements SignInOutService {

    private final CustomerRepositoryImpl customerRepository;

    private final HttpSession session;

    private final CustomerServiceImpl customerService;

    private final ObjectMapper objectMapper=new ObjectMapper();



    public SignInOutServiceImpl(CustomerRepositoryImpl customerRepository, HttpSession session, CustomerServiceImpl customerService) {
        this.customerRepository = customerRepository;
        this.session = session;
        this.customerService = customerService;
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        //session.invalidate();
        if (session.getAttribute("userNickName") == null) {
            List<Customer> customerList = customerRepository.getAllCustomers();
            Customer compare = null;
            Customer result=null;
            int calcul = 0;
            long id=0;

            if (signInRequest.getNickName() == null || signInRequest.getPassword() == null) {
                return new SignInResponse(false, "Both nickname and password need to be fill in", "Sending sign in webpage.");
            }

            for (int i = 0; i < customerList.size(); i++) {
                compare = customerList.get(i);
                if ((compare.getNickName().equals(signInRequest.getNickName())) && (compare.getPassword().equals(signInRequest.getPassword()))) {
                    id=compare.getId();
                    calcul = calcul + 1;
                    result=customerList.get(i);
                }
            }

            if (calcul == 1) {
                session.setAttribute("userNickName",signInRequest.getNickName());
                CustomerDto customerDto = customerService.addOnlineCustomer(id, result);
                if (customerDto != null) {
                    System.out.println("You are signed in as " + signInRequest.getNickName());
                    return new SignInResponse(true, "Hello " + session.getAttribute("userNickName") + " . You signed in successfully", "Sending you main webpage.");
                } else {
                    return new SignInResponse(false, "NullPointer Exception", "Sending sign in web page.");
                }
                //Session implementation
            }

            if (calcul > 1) {
                return new SignInResponse(false, "Error in database, we are working on reparation", "null");
            }

            if (calcul < 1) {
                return new SignInResponse(false, "Hello " + signInRequest.getNickName() + " . To start shopping you need to be registered.", "Sending registration webpage.");
            }


        } else {
            if (customerService.getOnlineCustomerByStringId(session.getAttribute("userNickName").toString())==null){
                customerService.addOnlineCustomerWithActiveSessionToOnlineCustomers(session.getAttribute("userNickName").toString());
                return new SignInResponse(true, "Hello " + session.getAttribute("userNickName") +
                        " ,you stayed signed in from last session, you can continue with your buying.", "Sending main web page.");
            }
            return new SignInResponse(false, "Hello " + session.getAttribute("userNickName") +
                    " ,you dont need to sign in again. If you want to sign with another account first you need to sign out", "Sending signIn webpage.");
        }



        return new SignInResponse(false, "Error", "Sending signIn webpage.");
    }

    @Override
    public SignInResponse signOut() {
        if (session.getAttribute("userNickName")!=null) {
            CustomerDto customerDto= customerService.getCustomerByNickName(session.getAttribute("userNickName").toString());
            customerService.deleteOnlineCustomerByStringId(customerDto.getId()+"");
            session.invalidate();
            return new SignInResponse(true, "You were successfully signed out", "Sending sign in web page");
        }else {
            return new SignInResponse(false,"You cant sign out when you are not online","Sending sign in web page");
        }
    }
}
