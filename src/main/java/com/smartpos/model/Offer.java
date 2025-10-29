package com.smartpos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String description;

    private Double discountPercentage;

    @Builder.Default
    private boolean active = true;

    // 🗓️ Add these two fields for Analytics filtering
    private LocalDate startDate;
    private LocalDate endDate;
}


