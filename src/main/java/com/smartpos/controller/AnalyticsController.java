package com.smartpos.controller;

import com.smartpos.repository.ProductRepository;
import com.smartpos.repository.InvoiceRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final ProductRepository productRepo;
    private final InvoiceRepository invoiceRepo;

    public AnalyticsController(ProductRepository productRepo, InvoiceRepository invoiceRepo) {
        this.productRepo = productRepo;
        this.invoiceRepo = invoiceRepo;
    }

    @GetMapping("/summary")
    public Map<String, Object> getSummary() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalProducts", productRepo.count());
        data.put("totalInvoices", invoiceRepo.count());
        return data;
    }
}

