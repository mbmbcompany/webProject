package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import sk.bielik.webProject.entityDto.ProductDto;
import sk.bielik.webProject.service.serviceImpl.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductServiceImpl productService;

    public ProductRestController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity getProducts(@Nullable @RequestParam("id") Long id){
        if (id==null){
        List<ProductDto> productDtos=productService.getAllProducts();
        return new ResponseEntity(productDtos, HttpStatus.OK);
        }else {
            ProductDto productDto=productService.getProductById(id);
            return new ResponseEntity(productDto,HttpStatus.OK);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1=productService.addProduct(productDto);
        return new ResponseEntity(productDto1,HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity updateProduct(@RequestBody ProductDto productDto,@RequestParam("id") Long id){
        ProductDto productDto1=productService.updateProduct(id,productDto);
        return new ResponseEntity(productDto1,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id")Long id){
        productService.deleteProductById(id);
        return new ResponseEntity("Product with id:"+id+" was successfully deleted. ",HttpStatus.OK);
    }


}
