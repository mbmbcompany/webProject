package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bielik.webProject.entity.BuyingOrder;
import sk.bielik.webProject.entityDto.BuyingOrderDto;

public interface BuyingOrderRepository extends JpaRepository<BuyingOrder,Long> {

}
