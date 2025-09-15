package com.xisufashion.xisufashion.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int discountPercent;

    private int quantity;

    private String color;

    private String images;

    private int numRatings;

    private String sizes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
