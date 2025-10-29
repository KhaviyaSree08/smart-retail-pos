package com.smartpos.service;

import com.smartpos.model.Offer;
import com.smartpos.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    // Get all active offers
    public List<Offer> getActiveOffers() {
        return offerRepository.findByActiveTrue();
    }

    // Get all offers (admin view)
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    // Add a new offer
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    // Deactivate or delete an offer
    public void deactivateOffer(Long id) {
        Optional<Offer> offerOpt = offerRepository.findById(id);
        if (offerOpt.isPresent()) {
            Offer offer = offerOpt.get();
            offer.setActive(false);
            offerRepository.save(offer);
        } else {
            throw new RuntimeException("Offer not found with ID: " + id);
        }
    }

    // Validate an offer code
    public Offer validateOffer(String code) {
        Offer offer = offerRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Invalid offer code"));

        if (!offer.isActive()) {
            throw new RuntimeException("Offer code is inactive");
        }

        return offer;
    }
}

