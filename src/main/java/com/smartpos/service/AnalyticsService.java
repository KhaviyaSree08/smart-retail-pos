package com.smartpos.service;

import com.smartpos.model.Analytics;
import com.smartpos.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public List<Analytics> getAllAnalytics() {
        return analyticsRepository.findAll();
    }

    public Optional<Analytics> getAnalyticsById(Long id) {
        return analyticsRepository.findById(id);
    }

    public Analytics saveAnalytics(Analytics analytics) {
        return analyticsRepository.save(analytics);
    }

    public void deleteAnalytics(Long id) {
        analyticsRepository.deleteById(id);
    }
}
