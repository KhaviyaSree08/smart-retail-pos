package com.smartpos.controller;

import com.smartpos.model.Customer;
import com.smartpos.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 🧭 Swagger API Endpoint (GET all)
    @Operation(summary = "Get all active customers")
    @GetMapping("/api")
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // 🌐 Thymeleaf Page - List Customers
    @GetMapping
    public String viewCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("customer", new Customer());
        return "customers";
    }

    // ➕ Add or Update Customer (Thymeleaf form)
    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    // 🗑 Delete Customer
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            Customer existing = customer.get();
            existing.setActive(false);
            customerService.saveCustomer(existing);
        }
        return "redirect:/customers";
    }

    // ✏️ Edit Customer
    @GetMapping("/edit/{id}")
    public String editCustomer(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer.orElse(new Customer()));
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }
}

