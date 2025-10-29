package com.smartpos.service;

import com.smartpos.model.Offer;
import com.smartpos.repository.OfferRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository repo;

    public OfferService(OfferRepository repo) {
        this.repo = repo;
    }

    public Offer save(Offer offer) {
        return repo.save(offer);
    }

    public List<Offer> getAll() {
        return repo.findAll();
    }

    public Optional<Offer> getByCode(String code) {
        return repo.findByCode(code);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
