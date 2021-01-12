package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.Customer;
import sk.bielik.webProject.entity.Product;
import sk.bielik.webProject.entity.Trolley;
import sk.bielik.webProject.entity.TrolleyItem;
import sk.bielik.webProject.repository.CustomerRepository;
import sk.bielik.webProject.repository.TrolleyRepository;

import java.util.List;
@Repository
public class TrolleyRepositoryImpl{

    private final CustomerRepository customerRepository;

    private final TrolleyRepository trolleyRepository;

    public TrolleyRepositoryImpl(CustomerRepository customerRepository, TrolleyRepository trolleyRepository) {
        this.customerRepository = customerRepository;
        this.trolleyRepository = trolleyRepository;
    }


    public Customer addProductToTrolley(Customer customer, Product product,long numberOfProducts) {
        //  product.getTrolleyList().add(customer.getTrolley());
        TrolleyItem trolleyItem=new TrolleyItem();
        // trolleyItem.setTrolley(customer.getTrolley());
        trolleyItem.setProduct(product);
        trolleyItem.setNumberOfPieces(numberOfProducts);
        customer.getTrolley().getTrolleyItems().add(trolleyItem);
        return customerRepository.save(customer);
    }


    public void deleteProductFromTrolley(Customer customer,Long trolleyItemId) {
        if (trolleyItemId == null) {
            customer.getTrolley().getTrolleyItems().removeAll(customer.getTrolley().getTrolleyItems());
        } else {
            List<TrolleyItem> trolleyItems=customer.getTrolley().getTrolleyItems();
            TrolleyItem trolleyItem=null;
            for (TrolleyItem item:trolleyItems){
                if (item.getId()==trolleyItemId){
                    trolleyItem=item;
                }
            }
            if (trolleyItem==null){
                throw new NullPointerException("Item with thiis id is not in the trolley");
            }

            customer.getTrolley().getTrolleyItems().remove(trolleyItem);
        }
    }

    public boolean buyProducts(Customer customer) {
        List<TrolleyItem> productList=customer.getTrolley().getTrolleyItems();
        boolean success=productList.removeAll(productList);
        return success;
    }


    public List<TrolleyItem> getAllProductsFromTrolley(Customer customer) {
        return customer.getTrolley().getTrolleyItems();
    }


    public Trolley addTrolleyToDatabase(Trolley trolley) {
        return trolleyRepository.save(trolley);
    }

    public void deleteItemFromTrolleyByItemOrder(Customer customer,int item) throws Exception {
        if (item<1 || item>customer.getTrolley().getTrolleyItems().size()+1){
            throw new Exception("Invalid index");
        }else {
            customer.getTrolley().getTrolleyItems().remove(item-1);
        }
    }
}
