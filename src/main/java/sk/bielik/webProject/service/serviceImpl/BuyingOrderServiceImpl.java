package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entity.enums.OrderStage;
import sk.bielik.webProject.entityDto.BuyingOrderDto;
import sk.bielik.webProject.entityDto.InvoiceDto;
import sk.bielik.webProject.repository.repositoryImp.BuyingOrderRepositoryImpl;
import sk.bielik.webProject.request.UpdateOrderedBuyingOrderRequest;
import sk.bielik.webProject.service.BuyingOrderMapperImpl;
import sk.bielik.webProject.service.BuyingOrderService;
@Service
public class BuyingOrderServiceImpl implements BuyingOrderService {

    private final BuyingOrderRepositoryImpl buyingOrderRepository;

    private final BuyingOrderMapperImpl buyingOrderMapper;

    public BuyingOrderServiceImpl(BuyingOrderRepositoryImpl buyingOrderRepository, BuyingOrderMapperImpl buyingOrderMapper) {
        this.buyingOrderRepository = buyingOrderRepository;
        this.buyingOrderMapper = buyingOrderMapper;
    }

    @Override
    public BuyingOrderDto save(BuyingOrderDto buyingOrderDto) {
        return buyingOrderMapper.mapBuyingOrderToBuyingOrderDto(buyingOrderRepository.save(buyingOrderMapper.mapBuyingOrderDtoToBuyingOrder(buyingOrderDto)));
    }

    @Override
    public BuyingOrderDto findById(long id) {
        return buyingOrderMapper.mapBuyingOrderToBuyingOrderDto(buyingOrderRepository.findById(id));
    }

    @Override
    public void deleteById(long id) {
        buyingOrderRepository.deleteById(id);
    }

    @Override
    public BuyingOrderDto updateOrderedBuyingOrder(UpdateOrderedBuyingOrderRequest updateOrderedBuyingOrderRequest) {
        BuyingOrderDto buyingOrderDtoFromDb=buyingOrderMapper.mapBuyingOrderToBuyingOrderDto(buyingOrderRepository.findById(updateOrderedBuyingOrderRequest.getBuyingOrderId()));
        buyingOrderDtoFromDb.setPayment(updateOrderedBuyingOrderRequest.isPayment());
        if (!updateOrderedBuyingOrderRequest.getBuyingOrderStage().isEmpty() || updateOrderedBuyingOrderRequest.getBuyingOrderStage()!=null){
        buyingOrderDtoFromDb.setStageOfOrder(OrderStage.valueOf(updateOrderedBuyingOrderRequest.getBuyingOrderStage().toUpperCase()));
        }
        if (buyingOrderDtoFromDb.getStageOfOrder()==OrderStage.SHIPPED && buyingOrderDtoFromDb.isPayment()){
            buyingOrderDtoFromDb.setStageOfOrder(OrderStage.SUCCESSFULLY_FINISHED);
        }
        BuyingOrderDto buyingOrderDto=buyingOrderMapper.mapBuyingOrderToBuyingOrderDto(buyingOrderRepository.save(buyingOrderMapper.mapBuyingOrderDtoToBuyingOrder(buyingOrderDtoFromDb)));
        return buyingOrderDto;
    }
}
