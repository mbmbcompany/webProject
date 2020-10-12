package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.CustomerWithoutPasswordDto;
import sk.bielik.webProject.request.AddProductToTrolleyRequest;
import sk.bielik.webProject.response.AddProductToTrolleyResponse;
import sk.bielik.webProject.service.TrolleyService;

@Service
public class TrolleyServiceImpl implements TrolleyService{

    private final CustomerServiceImpl customerService;

    private final ProductServiceImpl productService;

    public TrolleyServiceImpl(CustomerServiceImpl customerService, ProductServiceImpl productService) {
        this.customerService = customerService;
        this.productService = productService;
    }

    @Override
    public AddProductToTrolleyResponse addProductToTrolley(AddProductToTrolleyRequest addProductToTrolleyRequest) {

        long customerId=addProductToTrolleyRequest.getCustomerId();
        long productId=addProductToTrolleyRequest.getProductId();
        long numberOfProducts=addProductToTrolleyRequest.getNumberOfProducts();

        CustomerWithoutPasswordDto customerWithoutPasswordDto=customerService.getCustomerById(customerId);
        if (customerWithoutPasswordDto==null){
            return new AddProductToTrolleyResponse(false,"Internal server error,customer not found in database.","Sending signIn web page.");
        }


    }
}
