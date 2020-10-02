package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.*;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
