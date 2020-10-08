package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.service.serviceImpl.SignInOutServiceImpl;
import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;

@RestController
@RequestMapping("/sign")
public class SignInOutRestController {

    private final SignInOutServiceImpl signInService;

    public SignInOutRestController(SignInOutServiceImpl signInService) {
        this.signInService = signInService;
    }

    @PostMapping("/in")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest){
        SignInResponse signInResponse=signInService.signIn(signInRequest);

//        if (  session.getAttribute("userNickName")==null){
//        if (signInResponse.isSuccess()){
//            session.setAttribute("userNickName",signInRequest.getNickName());
//            System.out.println("You are signed in as "+signInRequest.getNickName());
//        }
//        }else {
//            System.out.println
//                    ("Hello "+session.getAttribute("userNickName")+
//                            " ,you dont need to sign in again. If you want to sign with another account first you need to sign out");
//        }

        if (signInResponse.isSuccess()){
        return new ResponseEntity(signInResponse.getMessage()+" "+signInResponse.getWebPage(), HttpStatus.OK);
        }else {
            return new ResponseEntity(signInResponse.getMessage()+" "+signInResponse.getWebPage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/out")
    public ResponseEntity signOut(){
       SignInResponse signInResponse= signInService.signOut();
        return new ResponseEntity(signInResponse.getMessage(),HttpStatus.OK);
    }
}
