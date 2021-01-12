package sk.bielik.webProject.service.serviceImpl;

import org.springframework.stereotype.Service;
import sk.bielik.webProject.entityDto.InvoiceDto;
import sk.bielik.webProject.repository.repositoryImp.InvoiceRepositoryImpl;
import sk.bielik.webProject.service.InvoiceMapperImpl;
import sk.bielik.webProject.service.InvoiceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceMapperImpl invoiceMapper;

    private final InvoiceRepositoryImpl invoiceRepository;

    public InvoiceServiceImpl(InvoiceMapperImpl invoiceMapper, InvoiceRepositoryImpl invoiceRepository) {
        this.invoiceMapper = invoiceMapper;
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public InvoiceDto save(InvoiceDto invoiceDto) {
        return invoiceMapper.mapInvoiceToInvoiceDto(invoiceRepository.save(invoiceMapper.mapInvoiceDtoToInvoice(invoiceDto)));
    }

    @Override
    public InvoiceDto findById(long id) {
        return invoiceMapper.mapInvoiceToInvoiceDto(invoiceRepository.findById(id));
    }

    @Override
    public void deleteById(long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceDto> findAll() {
        return invoiceRepository.findAll().stream().map(invoice -> invoiceMapper.mapInvoiceToInvoiceDto(invoice)).collect(Collectors.toList());
    }
}
