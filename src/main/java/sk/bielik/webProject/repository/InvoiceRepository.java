package sk.bielik.webProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.bielik.webProject.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
