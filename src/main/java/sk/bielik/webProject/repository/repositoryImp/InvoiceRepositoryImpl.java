package sk.bielik.webProject.repository.repositoryImp;

import org.springframework.stereotype.Repository;
import sk.bielik.webProject.entity.Invoice;
import sk.bielik.webProject.repository.InvoiceRepository;

import java.util.List;

@Repository
public class InvoiceRepositoryImpl {

    private final InvoiceRepository invoiceRepository;

    public InvoiceRepositoryImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice save(Invoice invoice){
        return invoiceRepository.save(invoice);
    }

    public Invoice findById(long id){
        return invoiceRepository.findById(id).get();
    }

    public void deleteById(long id){
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> findAll(){
        return invoiceRepository.findAll();
    }
}
