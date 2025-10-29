package com.smartpos.service;

import com.smartpos.model.Invoice;
import com.smartpos.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository repo;

    public InvoiceService(InvoiceRepository repo) {
        this.repo = repo;
    }

    public Invoice save(Invoice invoice) {
        return repo.save(invoice);
    }

    public List<Invoice> getAll() {
        return repo.findAll();
    }

    public Invoice getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

