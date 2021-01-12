package sk.bielik.webProject.service;

import sk.bielik.webProject.entityDto.InvoiceDto;

import java.util.List;

public interface InvoiceService {

    InvoiceDto save(InvoiceDto invoiceDto);

    InvoiceDto findById(long id);

    void deleteById(long id);

    List<InvoiceDto> findAll();
}
