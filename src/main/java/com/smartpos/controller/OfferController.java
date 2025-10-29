package com.smartpos.controller;

import com.smartpos.model.Offer;
import com.smartpos.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // Swagger API
    @Operation(summary = "Get all offers")
    @GetMapping("/api")
    @ResponseBody
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    // Thymeleaf UI
    @GetMapping
    public String listOffers(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers/list";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("offer", new Offer());
        return "offers/new";
    }

    @PostMapping
    public String createOffer(@ModelAttribute Offer offer) {
        offerService.createOffer(offer);
        return "redirect:/offers";
    }

    @GetMapping("/edit/{id}")
    public String editOffer(@PathVariable Long id, Model model) {
        Offer offer = offerService.getOfferById(id)
                .orElseThrow(() -> new RuntimeException("Offer not found"));
        model.addAttribute("offer", offer);
        return "offers/edit";
    }

    @PostMapping("/update/{id}")
    public String updateOffer(@PathVariable Long id, @ModelAttribute Offer offer) {
        offerService.updateOffer(id, offer);
        return "redirect:/offers";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return "redirect:/offers";
    }
}




