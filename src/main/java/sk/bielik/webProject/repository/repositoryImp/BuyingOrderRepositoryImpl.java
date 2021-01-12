package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.BuyingOrder;
import sk.bielik.webProject.repository.BuyingOrderRepository;
@Repository
public class BuyingOrderRepositoryImpl {

    private final BuyingOrderRepository buyingOrderRepository;

    public BuyingOrderRepositoryImpl(BuyingOrderRepository buyingOrderRepository) {
        this.buyingOrderRepository = buyingOrderRepository;
    }

    public BuyingOrder save(BuyingOrder buyingOrder){
       return buyingOrderRepository.save(buyingOrder);
    }

    public void deleteById(long id){
        buyingOrderRepository.deleteById(id);
    }

    public BuyingOrder findById(long id){
        return buyingOrderRepository.findById(id).get();
    }

}
