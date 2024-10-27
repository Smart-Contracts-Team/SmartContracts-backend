package com.SmartContracts.upc.smartcontract.model;

import com.SmartContracts.upc.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="services")
public class ServiceU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name="price",nullable = false)
    private Float price;

    @Column(name="stars",nullable = true)
    private int starts;

    @Column(name="photo",nullable = false)
    private String photo;

    @Column(name="state",nullable = false) // abierto, cerrado, ocupado
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

}
