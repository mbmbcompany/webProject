package sk.bielik.webProject.controller.restConroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.serviceImpl.TrolleyServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/trolley")
public class TrolleyRestController {

    private final TrolleyServiceImpl trolleyService;

    public TrolleyRestController(TrolleyServiceImpl trolleyService) {
        this.trolleyService = trolleyService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProductToTrolley(HttpServletRequest request, HttpServletResponse response, @RequestBody AddProductToTrolleyRequest addProductToTrolleyRequest) throws JsonProcessingException {
        AddProductToTrolleyResponse addProductToTrolleyResponse=trolleyService.addProductToTrolley(addProductToTrolleyRequest,request,response);
        return new ResponseEntity(addProductToTrolleyResponse, HttpStatus.OK);
    }
    @GetMapping("/getAllProducts/{id}")
    public ResponseEntity getAllProductsFromTrolleyByCustomer(@PathVariable("id") long customerId){
        List<TrolleyItem> productBasicInfoDtoList=trolleyService.getAllProductsFromTrolley(customerId);
        return new ResponseEntity(productBasicInfoDtoList,HttpStatus.OK);
    }
}
