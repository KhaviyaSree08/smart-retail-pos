package com.smartpos.controller;

import com.smartpos.model.Invoice;
import com.smartpos.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    // ---------- REST ENDPOINTS ----------

    // ✅ List all invoices (API)
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    // ✅ Get single invoice by ID (API)
    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
        return invoice.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Create a new invoice (API)
    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.saveInvoice(invoice));
    }

    // ✅ Delete invoice (API)
    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- THYMELEAF VIEWS ----------

    // ✅ Show all invoices on web page
    @GetMapping
    public String showInvoicesPage(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());
        return "invoices";  // → src/main/resources/templates/invoices.html
    }

    // ✅ View specific invoice in HTML
    @GetMapping("/{id}")
    public String showInvoiceDetails(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceById(id).orElse(null));
        return "invoice-details"; // → src/main/resources/templates/invoice-details.html
    }
}
