package sk.bielik.webProject.request;

import com.sun.istack.NotNull;
import sk.bielik.webProject.entityDto.*;

public class RegistrationRequest {

    @NotNull
   private CustomerDto customerDto;
    @NotNull
    private String passwordVerif;

    public RegistrationRequest(CustomerDto customerDto, String passwordVerif) {
        this.customerDto = customerDto;
        this.passwordVerif = passwordVerif;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public String getPasswordVerif() {
        return passwordVerif;
    }

    public void setPasswordVerif(String passwordVerif) {
        this.passwordVerif = passwordVerif;
    }
}
