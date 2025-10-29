package com.smartpos.controller;

import com.smartpos.model.Analytics;
import com.smartpos.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    // ✅ REST API for Swagger
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<Analytics>> getAllAnalytics() {
        return ResponseEntity.ok(analyticsService.getAllAnalytics());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Analytics> getAnalyticsById(@PathVariable Long id) {
        Optional<Analytics> data = analyticsService.getAnalyticsById(id);
        return data.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api")
    @ResponseBody
    public ResponseEntity<Analytics> createAnalytics(@RequestBody Analytics analytics) {
        return ResponseEntity.ok(analyticsService.saveAnalytics(analytics));
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAnalytics(@PathVariable Long id) {
        analyticsService.deleteAnalytics(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Thymeleaf views
    @GetMapping
    public String viewAnalytics(Model model) {
        model.addAttribute("analytics", analyticsService.getAllAnalytics());
        return "analytics";  // → src/main/resources/templates/analytics.html
    }

    @GetMapping("/{id}")
    public String viewAnalyticsDetails(@PathVariable Long id, Model model) {
        model.addAttribute("analytic", analyticsService.getAnalyticsById(id).orElse(null));
        return "analytics-details"; // → src/main/resources/templates/analytics-details.html
    }
}


