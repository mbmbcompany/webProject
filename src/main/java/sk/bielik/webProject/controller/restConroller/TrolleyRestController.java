package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.serviceImpl.TrolleyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/trolley")
public class TrolleyRestController {

    private final TrolleyServiceImpl trolleyService;

    public TrolleyRestController(TrolleyServiceImpl trolleyService) {
        this.trolleyService = trolleyService;
    }

    @PostMapping
    public ResponseEntity addProductToTrolley(HttpServletRequest request, HttpServletResponse response, @RequestBody AddProductToTrolleyRequest addProductToTrolleyRequest){
        AddProductToTrolleyResponse addProductToTrolleyResponse=trolleyService.addProductToTrolley(addProductToTrolleyRequest,request,response);
        return new ResponseEntity(addProductToTrolleyResponse, HttpStatus.OK);
    }
}
