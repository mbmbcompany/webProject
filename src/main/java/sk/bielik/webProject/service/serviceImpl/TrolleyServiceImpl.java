package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.entityDto.ProductBasicInfoDto;
import sk.bielik.webProject.entityDto.ProductDto;
import sk.bielik.webProject.repository.repositoryImp.TrolleyRepositoryImpl;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.CustomerMapperImpl;
import sk.bielik.webProject.service.ProductMapperImpl;
import sk.bielik.webProject.service.TrolleyService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TrolleyServiceImpl implements TrolleyService{

    private final CustomerServiceImpl customerService;

    private final ProductServiceImpl productService;

    private final ProductMapperImpl productMapper;

    private final CustomerMapperImpl customerMapper;

    private final TrolleyRepositoryImpl trolleyRepository;

    public TrolleyServiceImpl(CustomerServiceImpl customerService, ProductServiceImpl productService, ProductMapperImpl productMapper, CustomerMapperImpl customerMapper, TrolleyRepositoryImpl trolleyRepository) {
        this.customerService = customerService;
        this.productService = productService;
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
        this.trolleyRepository = trolleyRepository;
    }

    @Override
    public AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest, HttpServletRequest request,HttpServletResponse response) {

        long customerId=addProductToTrolleyRequest.getCustomerId();
        long productId=addProductToTrolleyRequest.getProductId();
        long numberOfProducts=addProductToTrolleyRequest.getNumberOfProducts();

        CustomerWithoutPasswordDto customerWithoutPasswordDto=customerService.getCustomerById(customerId);

        if (customerWithoutPasswordDto==null){
            return new AddProductToTrolleyResponse(false,"Internal server error,customer not found in database.","Sending signIn web page.");
        }

        ProductBasicInfoDto productBasicInfoDto=productService.getProductById(productId);
        if (productBasicInfoDto==null){
            return new AddProductToTrolleyResponse(false,"Internal server error, product not found in database","Sending signIn web page");
        }

        if (numberOfProducts>productBasicInfoDto.getNumberOfPiecesInStock()){
            return new AddProductToTrolleyResponse(false,"Dont enought products on stock, maximum number we can ship you from stock is"+productBasicInfoDto.getNumberOfPiecesInStock()+" .","Sending main web page.");
        }
        Cookie[] cookies=request.getCookies();
        if (cookies!=null) {
            for (int i=0;i<cookies.length;i++){
                System.out.println(cookies[i].getValue());
            }
        }
        ProductDto productDto=productService.getProductDtoById(productId);
        productDto.setNumberOfPiecesInStock(productDto.getNumberOfPiecesInStock()-numberOfProducts);
        productDto.setAddedToTrolley(Timestamp.from(Instant.now()));
        productDto.getTrolleyList().add(customerWithoutPasswordDto.getTrolley());
        //productService.updateProduct(productDto.getId(),productDto);

//        customerWithoutPasswordDto.getTrolley().getProductList().add(productMapper.mapProductDtoToProduct(productDto));
//        customerService.saveCustomer(customerMapper.mapCustomerWithoutPasswordDtoToCustomerDto(customerWithoutPasswordDto));

        trolleyRepository.addProductToTrolley(customerMapper.mapCustomerWithoutPasswordDtoToCustomer(customerWithoutPasswordDto),productMapper.mapProductDtoToProduct(productDto));


        Cookie cookie=new Cookie("trolleyCookie",addProductToTrolleyRequest.getProductId()+":"+addProductToTrolleyRequest.getNumberOfProducts());
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);

        AddProductToTrolleyResponse addProductToTrolleyResponse= new AddProductToTrolleyResponse(true,"Added to the trolley","Sending main web page.");
        return addProductToTrolleyResponse;

    }

    @Override
    public void deleteProductFromTrolley(long productId, long numberOfProducts) {

    }

    @Override
    public List<ProductBasicInfoDto> buyProducts() {
        return null;
    }

    @Override
    public List<ProductBasicInfoDto> getAllProductsFromTrolley() {
        return null;
    }
}
