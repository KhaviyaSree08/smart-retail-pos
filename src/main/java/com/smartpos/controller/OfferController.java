package com.smartpos.controller;

import com.smartpos.model.Offer;
import com.smartpos.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/offers")
public class OfferController {
    private final OfferService service;

    public OfferController(OfferService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        return ResponseEntity.ok(service.save(offer));
    }

    @GetMapping
    public List<Offer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Offer> getByCode(@PathVariable String code) {
        return service.getByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

