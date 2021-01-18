package sk.bielik.webProject.service.serviceImpl;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.entity.enums.OrderStage;
import sk.bielik.webProject.entityDto.*;
import sk.bielik.webProject.repository.repositoryImp.CustomerRepositoryImpl;
import sk.bielik.webProject.repository.repositoryImp.TrolleyRepositoryImpl;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrolleyServiceImpl implements TrolleyService{

    private final CustomerServiceImpl customerService;

    private final ProductServiceImpl productService;

    private final CustomerMapperImpl customerMapper;

    private final TrolleyRepositoryImpl trolleyRepository;

    private final TrolleyMapperImpl trolleyMapper;

    private final HttpSession session;

    private final CookieServiceImpl cookieService;

    private final BuyingOrderServiceImpl buyingOrderService;

    private final TrolleyItemMapperImpl trolleyItemMapper;





    public TrolleyServiceImpl(CustomerServiceImpl customerService, ProductServiceImpl productService, CustomerMapperImpl customerMapper, TrolleyRepositoryImpl trolleyRepository, TrolleyMapperImpl trolleyMapper, HttpSession session, CookieServiceImpl cookieService, BuyingOrderServiceImpl buyingOrderService, TrolleyItemMapperImpl trolleyItemMapper) {


        this.customerService = customerService;
        this.productService = productService;
        this.customerMapper = customerMapper;
        this.trolleyRepository = trolleyRepository;
        this.trolleyMapper = trolleyMapper;
        this.session = session;
        this.cookieService = cookieService;
        this.buyingOrderService = buyingOrderService;
        this.trolleyItemMapper = trolleyItemMapper;
    }

    @Override
    public AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest,HttpServletResponse response) throws JsonProcessingException {

        String nickName=session.getAttribute("userNickName").toString();
        CustomerDto customerDtoId=customerService.getCustomerByNickName(nickName);
        long customerId=customerDtoId.getId();
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
            return new AddProductToTrolleyResponse(false,"Dont enough products on stock, maximum number we can ship you from stock is"+productDto.getNumberOfPiecesInStock()+" .","Sending main web page.");
        }

        if (session.getAttribute("userNickName")==null){
            return new AddProductToTrolleyResponse(false,"For buying products you need to be signed in","Sending sign in web page");
        }

        productDto.setNumberOfPiecesInStock(productDto.getNumberOfPiecesInStock()-numberOfProducts);

        TrolleyItemDto trolleyItemDto=new TrolleyItemDto();
        trolleyItemDto.setAddingTime(Timestamp.from(Instant.now()));
        trolleyItemDto.setNumberOfPieces(numberOfProducts);
        trolleyItemDto.setProduct(productDto);


        customerDto.getTrolley().getTrolleyItems().add(trolleyItemDto);
        CustomerDto customerDto1=customerService.saveCustomer(customerDto);
        productService.save(productDto);

        cookieService.addTrolleyCookie(customerDto1,"trolley",60*60,response);

        AddProductToTrolleyResponse addProductToTrolleyResponse= new AddProductToTrolleyResponse(true,session.getAttribute("userNickName")+" you added product to the trolley successfully","Sending main web page.");
        return addProductToTrolleyResponse;


    }

    @Override
    public void deleteItemFromTrolley(Long trolleyItemId,HttpServletResponse response) {
        String nickName=session.getAttribute("userNickName").toString();

        if (nickName==null){
            throw new NullPointerException("You are not signed in, sending sign in web page.");
        }else {
            CustomerDto customerDto = customerService.getCustomerByNickName(nickName);
            trolleyRepository.deleteProductFromTrolley(customerMapper.mapCustomerDtoToCustomer(customerDto), trolleyItemId);
            customerService.saveCustomer(customerDto);
            cookieService.addTrolleyCookie(customerDto, "trolley", 60 * 60, response);
        }


    }

    @Override
    public void deleteItemByItemOrder(int item,HttpServletResponse response) throws Exception {
        String nickName=session.getAttribute("userNickName").toString();

        if (nickName==null){
            throw new NullPointerException("You are not signed in, sending sign in web page.");
        }  else {
            CustomerDto customerDto = customerService.getCustomerByNickName(nickName);
            trolleyRepository.deleteItemFromTrolleyByItemOrder(customerMapper.mapCustomerDtoToCustomer(customerDto),item);
            customerService.saveCustomer(customerDto);
            cookieService.addTrolleyCookie(customerDto, "trolley", 60 * 60, response);
        }
    }

    @Override
    public BuyingOrderDto buyProducts(HttpServletResponse response) {
        String nickName=session.getAttribute("userNickName").toString();
        if (nickName==null){
            throw  new NullPointerException("You are not signed in, sending sign in web page.");
        }
        BuyingOrderDto buyingOrderDto1=new BuyingOrderDto();
        InvoiceDto invoiceDto = new InvoiceDto();
        CustomerDto customerDto=customerService.getCustomerByNickName(nickName);

        BigDecimal priceWithVat=new BigDecimal(0);
        List<TrolleyItemDto> trolleyItemList=customerDto.getTrolley().getTrolleyItems();
        for (TrolleyItemDto item :
                trolleyItemList) {

            priceWithVat = priceWithVat.add(item.getProduct().getPrice().multiply(new BigDecimal(item.getNumberOfPieces())));
        }
        invoiceDto.setCredentials(customerDto.getCredentials());
        invoiceDto.getTrolleyItems().addAll(customerDto.getTrolley().getTrolleyItems());
        invoiceDto.setPriceWithVAT(priceWithVat.doubleValue());
        invoiceDto.setPriceWithoutVAT(priceWithVat.doubleValue()*0.88);
        buyingOrderDto1.setInvoice(invoiceDto);
        buyingOrderDto1.setPayment(false);
        buyingOrderDto1.setStageOfOrder(OrderStage.ORDERED);
        BuyingOrderDto buyingOrderDto=buyingOrderService.save(buyingOrderDto1);
        customerDto.getTrolley().getTrolleyItems().removeAll(customerDto.getTrolley().getTrolleyItems());
        customerService.saveCustomer(customerDto);
        cookieService.terminateCookie("trolley",response);
        return buyingOrderDto;
    }

    @Override
    public List<TrolleyItemDto> getAllProductsFromTrolley() {
        String nickName = session.getAttribute("userNickName").toString();
        if (nickName == null) {
            throw new NullPointerException("You are not signed in, sending sign in web page.");
        } else {
            CustomerDto customerDto = customerService.getCustomerByNickName(nickName);
            List<TrolleyItem> trolleyItems= trolleyRepository.getAllProductsFromTrolley(customerMapper.mapCustomerDtoToCustomer(customerDto));
            return trolleyItems.stream().map(trolleyItem -> trolleyItemMapper.mapTrolleyItemToTrolleyItemDto(trolleyItem)).collect(Collectors.toList());
        }
    }

    @Override
    public TrolleyDto addTrolleyToDatabase(TrolleyDto trolleyDto) {
        return trolleyMapper.mapTrolleyToTrolleyDto(trolleyRepository.addTrolleyToDatabase(trolleyMapper.mapTrolleyDtoToTrolley(trolleyDto)));

    }
}
