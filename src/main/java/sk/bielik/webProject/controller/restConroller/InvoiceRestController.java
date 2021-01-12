package sk.bielik.webProject.controller.restConroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.bielik.webProject.entityDto.InvoiceDto;
import sk.bielik.webProject.service.serviceImpl.InvoiceServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceRestController {

    private final InvoiceServiceImpl invoiceService;

    public InvoiceRestController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity findById(@RequestParam(value = "id",required = false) Long id){
        if (id!=null){
            InvoiceDto invoiceDto=invoiceService.findById(id);
            return new ResponseEntity(invoiceDto, HttpStatus.OK);
        }else {
            List<InvoiceDto> invoiceDtos=invoiceService.findAll();
            return new ResponseEntity(invoiceDtos,HttpStatus.OK);
        }
    }
}
