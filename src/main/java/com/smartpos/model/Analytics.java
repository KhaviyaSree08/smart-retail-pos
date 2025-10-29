package com.smartpos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metricName;
    private double metricValue;
    private String period; // e.g., "daily", "monthly"
}

