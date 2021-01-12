package sk.bielik.webProject.service;

import org.mapstruct.Mapper;
import sk.bielik.webProject.entity.Invoice;
import sk.bielik.webProject.entityDto.InvoiceDto;

@Mapper
public interface InvoiceMapper {

    Invoice mapInvoiceDtoToInvoice(InvoiceDto invoiceDto);

    InvoiceDto mapInvoiceToInvoiceDto(Invoice invoice);
}
