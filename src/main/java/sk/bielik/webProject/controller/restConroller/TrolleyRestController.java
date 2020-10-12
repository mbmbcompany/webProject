package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.serviceImpl.TrolleyServiceImpl;

@RestController
@RequestMapping("/trolley")
public class TrolleyRestController {

    private final TrolleyServiceImpl trolleyService;

    public TrolleyRestController(TrolleyServiceImpl trolleyService) {
        this.trolleyService = trolleyService;
    }

    @PostMapping
    public ResponseEntity addProductToTrolley(@RequestBody AddProductToTrolleyRequest addProductToTrolleyRequest){
        AddProductToTrolleyResponse addProductToTrolleyResponse=trolleyService.addProductToTrolley(addProductToTrolleyRequest);
        return new ResponseEntity(addProductToTrolleyResponse, HttpStatus.OK);
    }
}
