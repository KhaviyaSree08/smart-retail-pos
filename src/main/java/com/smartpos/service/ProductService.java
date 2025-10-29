package com.smartpos.service;

import com.smartpos.model.Product;
import com.smartpos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findBySku(String sku) {
        return repo.findBySku(sku);
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
