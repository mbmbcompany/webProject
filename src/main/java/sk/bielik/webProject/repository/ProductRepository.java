package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bielik.webProject.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
