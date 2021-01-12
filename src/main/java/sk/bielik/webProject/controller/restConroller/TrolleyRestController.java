package sk.bielik.webProject.controller.restConroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.BuyingOrderDto;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.TrolleyItemDto;
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
        AddProductToTrolleyResponse addProductToTrolleyResponse=trolleyService.addProductToTrolley(addProductToTrolleyRequest,response);
        return new ResponseEntity(addProductToTrolleyResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity getAllProductsFromTrolleyByCustomer(){
        List<TrolleyItemDto> productBasicInfoDtoList=trolleyService.getAllProductsFromTrolley();
        return new ResponseEntity(productBasicInfoDtoList,HttpStatus.OK);
    }

    @DeleteMapping("deleteItems")
    public ResponseEntity deleteItemsFromTrolley(@RequestParam(value = "id",required = false)Long id,HttpServletResponse response){
        trolleyService.deleteItemFromTrolley(id,response);
        return new ResponseEntity("Items were deleted",HttpStatus.OK);
    }

    @DeleteMapping("deleteItemByOrder/{i}")
    public ResponseEntity deleteItemByOrder(@PathVariable("i") int i,HttpServletResponse response) throws Exception {
        trolleyService.deleteItemByItemOrder(i,response);
        return new ResponseEntity("Item was deleted",HttpStatus.OK);
    }

    @GetMapping("/buyProducts")
    public ResponseEntity buyProducts(HttpServletResponse response){
        BuyingOrderDto buyingOrderDto=trolleyService.buyProducts(response);
        return new ResponseEntity(buyingOrderDto,HttpStatus.OK);
    }
}
