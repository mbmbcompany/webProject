package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.*;
import sk.bielik.webProject.repository.repositoryImp.*;
import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;
import sk.bielik.webProject.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SignInServiceImpl implements SignInService {

    private final CustomerRepositoryImpl customerRepository;

    private final HttpSession session;

    CustomerServiceImpl customerService;


    public SignInServiceImpl(CustomerRepositoryImpl customerRepository, HttpSession session) {
        this.customerRepository = customerRepository;
        this.session = session;
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        if (session.getAttribute("userNickName")==null) {
            List<Customer> customerList = customerRepository.getAllCustomers();

            int calcul = 0;

//        ListIterator iterator=customerList.listIterator();
//        while (iterator.hasNext()){
//            Customer compare=(Customer) iterator.next();
//            if (compare.getNickName()==signInRequest.getNickName() && compare.getPassword()==signInRequest.getPassword()){
//                calcul++;
//            }
//        }
            if (signInRequest.getNickName() == null || signInRequest.getPassword() == null) {
                return new SignInResponse(false, "Both nickname and password need to be fill in", "Sending sign in webpage.");
            }

            for (int i = 0; i < customerList.size(); i++) {
                Customer compare = customerList.get(i);
                if ((compare.getNickName().equals(signInRequest.getNickName())) && (compare.getPassword().equals(signInRequest.getPassword()))) {
                    calcul = calcul + 1;
                }
            }

            if (calcul == 1) {
                session.setAttribute("userNickName",signInRequest.getNickName());
                System.out.println("You are signed in as "+signInRequest.getNickName());
                return new SignInResponse(true, "Hello " + signInRequest.getNickName() + " . You signed in successfully", "Sending you main webpage.");
                //Session implementation
            }

            if (calcul > 1) {
                return new SignInResponse(false, "Error in database, we are working on reparation", "null");
            }

            if (calcul < 1) {
                return new SignInResponse(false, "Hello " + signInRequest.getNickName() + " . To start shopping you need to be registered.", "Sending registration webpage.");
            }


        }else {
            return new SignInResponse(false, "Hello "+session.getAttribute("userNickName")+
                    " ,you dont need to sign in again. If you want to sign with another account first you need to sign out", "Sending signIn webpage.");
        }

        return new SignInResponse(false, "Error", "Sending signIn webpage.");
    }
}
