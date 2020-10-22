package sk.bielik.webProject.service.serviceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.repository.repositoryImp.TrolleyRepositoryImpl;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrolleyServiceImpl implements TrolleyService{

    private final CustomerServiceImpl customerService;

    private final ProductServiceImpl productService;

    private final ProductMapperImpl productMapper;

    private final CustomerMapperImpl customerMapper;

    private final TrolleyRepositoryImpl trolleyRepository;

    private final TrolleyMapperImpl trolleyMapper;

    private final ObjectMapper objectMapper;

    private final HttpSession session;

    public TrolleyServiceImpl(CustomerServiceImpl customerService, ProductServiceImpl productService, ProductMapperImpl productMapper, CustomerMapperImpl customerMapper, TrolleyRepositoryImpl trolleyRepository, TrolleyMapperImpl trolleyMapper, ObjectMapper objectMapper, HttpSession session) {


        this.customerService = customerService;
        this.productService = productService;
        this.productMapper = productMapper;
        this.customerMapper = customerMapper;
        this.trolleyRepository = trolleyRepository;
        this.trolleyMapper = trolleyMapper;
        this.objectMapper = objectMapper;
        this.session = session;
    }

    @Override
    public AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest, HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException {

//        Cookie userNameCookieRemove = new Cookie("trolleyCookie", "");
//        userNameCookieRemove.setMaxAge(0);
//        response.addCookie(userNameCookieRemove);
//        Cookie userNameCookieRemove1 = new Cookie("6", "");
//        userNameCookieRemove.setMaxAge(0);
//        response.addCookie(userNameCookieRemove1);
//        Cookie userNameCookieRemove2 = new Cookie("5", "");
//        userNameCookieRemove.setMaxAge(0);
//        response.addCookie(userNameCookieRemove2);

        long customerId=addProductToTrolleyRequest.getCustomerId();
        long productId=addProductToTrolleyRequest.getProductId();
        long numberOfProducts=addProductToTrolleyRequest.getNumberOfProducts();

        CustomerDto customerDto=customerService.getCustomerById(customerId);
        ProductDto productDto=productService.getProductDtoById(productId);




        if (customerDto==null){
            return new AddProductToTrolleyResponse(false,"Internal server error,customer not found in database.","Sending signIn web page.");
        }

        if (productDto==null){
            return new AddProductToTrolleyResponse(false,"Internal server error, product not found in database","Sending signIn web page");
        }

        if (numberOfProducts>productDto.getNumberOfPiecesInStock()){
            return new AddProductToTrolleyResponse(false,"Dont enought products on stock, maximum number we can ship you from stock is"+productDto.getNumberOfPiecesInStock()+" .","Sending main web page.");
        }

        if (session.getAttribute("userNickName")==null){
            return new AddProductToTrolleyResponse(false,"For buying products you need to be signed in","Sending sign in web page");
        }

//        if(!customerWithoutPasswordDto.getTrolley().getProductList().isEmpty()){
//            List<Product> productList=customerWithoutPasswordDto.getTrolley().getProductList();
//            Product searchedProduct=productList.stream().filter(product -> product.getId()==productId).findFirst().get();
//            if (searchedProduct!=null){
//                Cookie[] cookies=request.getCookies();
//                Cookie productCookie=null;
//                if (cookies!=null) {
//
//                    for (int i=0;i<cookies.length;i++){
//                        if (cookies[i].getName().equals("trolleyCookie")){
//                            productCookie=cookies[i];
//                            break;
//                        }
//                    }
//
//                    String[] cookieValueStringArray=productCookie.getValue().split("y");
//                            for (int j=0;j<cookieValueStringArray.length;j++) {
//
//                                String[] getProductId = cookieValueStringArray[j].split("x");
//
//                                if ((getProductId[0].equals(productId + "")) && (Long.parseLong(getProductId[1]) + numberOfProducts <= productDto.getNumberOfPiecesInStock())) {
//                                    long calcul = Long.parseLong(getProductId[1]) + numberOfProducts;
//                                    getProductId[1] = calcul + "";
//                                    cookieValueStringArray[j]=getProductId.toString();
//                                    break;
//                                }
//                            }
//
//
//                            productCookie.setValue(cookieValueStringArray.toString());
//                            response.addCookie(productCookie);
//
//                    productDto.setAddedToTrolley(Timestamp.from(Instant.now()));
//                    productDto.setNumberOfPiecesInTrolley(numberOfProducts+productDto.getNumberOfPiecesInTrolley());
//                    trolleyRepository.addProductToTrolley(customerMapper.mapCustomerWithoutPasswordDtoToCustomer(customerWithoutPasswordDto),productMapper.mapProductDtoToProduct(productDto));
//
//                                   // productDto.setNumberOfPiecesInStock(productDto.getNumberOfPiecesInStock()-numberOfProducts);
//                                    //productDto.setAddedToTrolley(Timestamp.from(Instant.now()));
//                                    //productDto.getTrolleyList().add(customerWithoutPasswordDto.getTrolley());
//                                    return new AddProductToTrolleyResponse(true,"Added another pieces of product in your trolley to the trolley.","Sending main web page.");
//                                }
//                            }
//                        }
//
//
//            //uz jr v kosiku taky produkt no chce pridat dalsie kusy, vyhladam aj v databaze aj v cookies a pridam
//
//        productDto.setNumberOfPiecesInStock(productDto.getNumberOfPiecesInStock()-numberOfProducts);
//      productDto=productService.updateProduct(productDto.getId(),productDto);
//
//
//      productDto.setAddedToTrolley(Timestamp.from(Instant.now()));
//        productDto.setNumberOfPiecesInTrolley(numberOfProducts);
//        trolleyRepository.addProductToTrolley(customerMapper.mapCustomerWithoutPasswordDtoToCustomer(customerWithoutPasswordDto),productMapper.mapProductDtoToProduct(productDto));
////
//        Cookie[] cookies=request.getCookies();
//        int calcul=0;
//        Cookie searchedCookie=null;
//        if (cookies!=null) {
//            for (int i=0;i<cookies.length;i++){
//                System.out.println(cookies[i].getValue()+cookies[i].getName());
//                if (cookies[i].getName().equals("trolleyCookie")){
//                    calcul++;
//                    searchedCookie=cookies[i];
//                }
//            }
//        }
//
//        if (calcul==0) {
//            Cookie cookie = new Cookie("trolleyCookie", productId+ "x" + numberOfProducts+ "y");
//            cookie.setMaxAge(60 * 60);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//        }else {
//            searchedCookie.setValue(searchedCookie.getValue().concat(productId + "x" + numberOfProducts+ "y"));
//            response.addCookie(searchedCookie);
//        }


        productDto.setNumberOfPiecesInStock(productDto.getNumberOfPiecesInStock()-numberOfProducts);
        ProductDto updatedProductDto=productService.updateProduct(productDto.getId(),productDto);


        TrolleyItem trolleyItem=new TrolleyItem();
        trolleyItem.setAddingTime(Timestamp.from(Instant.now()));
        trolleyItem.setNumberOfPieces(numberOfProducts);
        trolleyItem.setProduct(productMapper.mapProductDtoToProduct(productDto));
        trolleyItem.setTrolley(customerDto.getTrolley());
//
//        updatedProductDto.setAddedToTrolley(Timestamp.from(Instant.now()));
//        updatedProductDto.setNumberOfPiecesInTrolley(numberOfProducts);
       // customerDto.getTrolley().getProduct().add(productMapper.mapProductDtoToProduct(updatedProductDto));
        customerDto.getTrolley().getTrolleyItems().add(trolleyItem);
        customerService.saveCustomer(customerDto);



        List<TrolleyItem> cookieTrolley=customerDto.getTrolley().getTrolleyItems();
        String cookieStringVAlue="";
        for (TrolleyItem trolleyItem1:cookieTrolley){
            cookieStringVAlue=cookieStringVAlue+trolleyItem1.getId()+"-";
        }


        Cookie cookie=new Cookie("trolley",cookieStringVAlue);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        Cookie[]cookies=request.getCookies();
        for (Cookie cookie1:cookies){
            if (cookie1.getName().equals("trolley")){
                System.out.println(cookie1.getValue());
                break;
            }
        }

        AddProductToTrolleyResponse addProductToTrolleyResponse= new AddProductToTrolleyResponse(true,session.getAttribute("userNickName")+" you added product to the trolley successfully","Sending main web page.");
        return addProductToTrolleyResponse;

    }

    @Override
    public void deleteProductFromTrolley(long productId, long numberOfProducts) {

    }

    @Override
    public List<TrolleyItem> buyProducts() {
        return null;
    }

    @Override
    public List<TrolleyItem> getAllProductsFromTrolley(long customerId) {
        CustomerDto customerWithoutPasswordDto=customerService.getCustomerById(customerId);
        return trolleyRepository.getAllProductsFromTrolley(customerMapper.mapCustomerDtoToCustomer(customerWithoutPasswordDto));
    }

    @Override
    public TrolleyDto addTrolleyToDatabase(TrolleyDto trolley) {
        return trolleyMapper.mapTrolleyToTrolleyDto(trolleyMapper.mapTrolleyDtoToTrolley(trolley));
    }
}
