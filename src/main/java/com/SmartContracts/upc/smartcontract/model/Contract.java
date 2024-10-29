package com.SmartContracts.upc.smartcontract.model;

import com.SmartContracts.upc.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="contracts")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name="start_date",nullable = false)
    private LocalDate startDate;

    @Column(name="final_date",nullable = false)
    private LocalDate finalDate;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "business_id", nullable = true)
    private Long businessId;

    @Column(name = "influencer_id", nullable = true)
    private Long influencerId;

    // Relación Many-to-Many con ServiceU
    @ManyToMany
    @JoinTable(
            name = "contract_services",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<ServiceU> services = new ArrayList<>();
}
