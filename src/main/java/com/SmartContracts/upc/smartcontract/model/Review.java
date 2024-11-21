package com.SmartContracts.upc.smartcontract.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name="stars",nullable = true)
    private int stars;

    @Column(name="author_id",nullable = false)
    private Long authorId;

    @Column(name="influencer_id",nullable = false)
    private Long influencerId;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceU service;
}
