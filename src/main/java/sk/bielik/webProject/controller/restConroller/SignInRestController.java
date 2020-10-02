package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.bielik.webProject.service.serviceImpl.SignInServiceImpl;
import sk.bielik.webProject.request.*;
import sk.bielik.webProject.response.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@RestController
@RequestMapping("/signIn")
public class SignInRestController {

    private final SignInServiceImpl signInService;

    public SignInRestController(SignInServiceImpl signInService) {
        this.signInService = signInService;
    }

    @PostMapping
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
}
