package com.nhatdang2604.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "attributes")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stockQty;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private ProductPrice productPrice;

    @ManyToOne
    @JoinColumn(name = "sku_id")
    private ProductSku productSku;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
