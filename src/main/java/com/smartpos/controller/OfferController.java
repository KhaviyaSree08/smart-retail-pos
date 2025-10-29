package com.smartpos.controller;

import com.smartpos.model.Offer;
import com.smartpos.service.OfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin("*")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // ✅ Get all active offers
    @GetMapping("/active")
    public List<Offer> getActiveOffers() {
        return offerService.getActiveOffers();
    }

    // 🧾 Get all offers (admin only)
    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    // ➕ Create a new offer
    @PostMapping
    public Offer createOffer(@RequestBody Offer offer) {
        return offerService.createOffer(offer);
    }

    // 🚫 Deactivate an offer
    @PutMapping("/{id}/deactivate")
    public String deactivateOffer(@PathVariable Long id) {
        offerService.deactivateOffer(id);
        return "Offer deactivated successfully";
    }

    // 🧩 Validate an offer code (for checkout)
    @GetMapping("/validate/{code}")
    public Offer validateOffer(@PathVariable String code) {
        return offerService.validateOffer(code);
    }
}



