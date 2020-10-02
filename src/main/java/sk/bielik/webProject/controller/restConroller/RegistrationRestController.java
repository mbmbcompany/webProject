package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;
import sk.bielik.webProject.service.serviceImpl.*;

@RestController
@RequestMapping("/registration")
public class RegistrationRestController {

    private final RegistrationServiceImpl registrationService;

    public RegistrationRestController(RegistrationServiceImpl registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest){
        RegistrationResponse registrationResponse=registrationService.register(registrationRequest);
        return new ResponseEntity(registrationResponse.getMessage()+" "+registrationResponse.getWebPage(), HttpStatus.CREATED);
    }
}
