package com.smartpos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // e.g. FESTIVE10
    private Double discountPercent;
    private LocalDate validFrom;
    private LocalDate validTo;
    private boolean active;
}
