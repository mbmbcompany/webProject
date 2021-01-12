package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.entityDto.BuyingOrderDto;
import sk.bielik.webProject.request.UpdateOrderedBuyingOrderRequest;
import sk.bielik.webProject.service.serviceImpl.BuyingOrderServiceImpl;

@RestController
@RequestMapping("/buyingOrder")
public class BuyingOrderRestController {

    private final BuyingOrderServiceImpl buyingOrderService;

    public BuyingOrderRestController(BuyingOrderServiceImpl buyingOrderService) {
        this.buyingOrderService = buyingOrderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id")long id){
        BuyingOrderDto buyingOrderDto=buyingOrderService.findById(id);
        return new ResponseEntity(buyingOrderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id")long id){
        buyingOrderService.deleteById(id);
      return   new ResponseEntity("Order was deleted",HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity updateBuyingOrder(@RequestBody UpdateOrderedBuyingOrderRequest updateOrderedBuyingOrderRequest){
        BuyingOrderDto buyingOrderDto=buyingOrderService.updateOrderedBuyingOrder(updateOrderedBuyingOrderRequest);
        return new ResponseEntity(buyingOrderDto,HttpStatus.OK);
    }
}
