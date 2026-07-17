package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "experience")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Experience {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String company;

    private String role;
    private String type; // Internship, Full-time, Freelance, Part-time
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean current;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String companyLogoUrl;
    private String location;
    private Integer sortOrder;
}
