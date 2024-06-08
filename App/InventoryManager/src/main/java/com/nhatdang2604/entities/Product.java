package com.nhatdang2604.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Setter
@Getter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @MapKey(name = "id")
    private Map<Long, Category> categories;

    @OneToMany(mappedBy = "product")
    @MapKeyJoinColumn(name = "attribute_id")
    private Map<Attribute, ProductAttribute> productAttributes;
}
