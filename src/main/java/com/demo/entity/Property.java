package com.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "property_name", nullable = false, length = 200)
    private String propertyName;

    @Column(name = "bedrooms", nullable = false)
    private Long bedrooms;

    @Column(name = "bathrooms", nullable = false)
    private Long bathrooms;

    @Column(name = "beds", nullable = false)
    private Long beds;

    @Column(name = "guests", nullable = false)
    private Long guests;

    @Column(name = "nightly_price", nullable = false)
    private Long nightlyPrice;

}