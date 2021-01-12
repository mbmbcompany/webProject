package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;
import sk.bielik.webProject.service.RegistrationService;
import java.util.List;
import java.util.ListIterator;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final CustomerServiceImpl customerService;

    public RegistrationServiceImpl(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @Override
    public RegistrationResponse register(RegistrationRequest registrationRequest) {

        if (registrationRequest.getCustomerDto().getNickName()==null ||
                registrationRequest.getCustomerDto().getPassword()==null ||
                registrationRequest.getCustomerDto().getCredentials().getFirstName()==null ||
                registrationRequest.getCustomerDto().getCredentials().getAdress()==null ||
                registrationRequest.getCustomerDto().getCredentials().getEmail()==null ||
                registrationRequest.getCustomerDto().getCredentials().getPhone_number()==null ||
                registrationRequest.getCustomerDto().getCredentials().getSurename()==null ||
                registrationRequest.getPasswordVerif()==null){
            return new RegistrationResponse(false,"All fields need to be filled in.","Sending registration web page.");
        }

        if (registrationRequest.getCustomerDto().getPassword().chars().count()<8){
            return new RegistrationResponse(false,"Password needs to have at lest 8 characters.","Sending registration web page.");
        }

        if (!registrationRequest.getCustomerDto().getPassword().equals(registrationRequest.getPasswordVerif())){
            return new RegistrationResponse(false,"Password and password verification doesn t match.","Sending registration web page");
        }

        List<CustomerWithoutPasswordDto> customerWithoutPasswordDtoList =customerService.getAllCustomers();
        ListIterator listIterator= customerWithoutPasswordDtoList.listIterator();
        int calcul=0;
        while (listIterator.hasNext()){
            CustomerWithoutPasswordDto customerWithoutPasswordDto =(CustomerWithoutPasswordDto) listIterator.next();
            if (customerWithoutPasswordDto.getNickName().equals(registrationRequest.getCustomerDto().getNickName())){
                calcul++;
            }
        }

        if (calcul==1){
            return new RegistrationResponse(false,"You need to chose diiferent nickname, this is already occupied.","Sending registration web page.");
        }
        if (calcul>1){
            return new RegistrationResponse(false,"Error","Sending registration web page.");
        }
        if (calcul<1){
            TrolleyDto trolleyDto=new TrolleyDto();
            CustomerDto customerDto=registrationRequest.getCustomerDto();
            customerDto.setTrolley(trolleyDto);
            customerService.saveCustomer(customerDto);
            return new RegistrationResponse(true,"Hello "+registrationRequest.getCustomerDto().getNickName()+" ,you have been successfully registered","Sending main web page.");
        }

        return new RegistrationResponse(false,"Error","Sending registration web page.");

    }
}
