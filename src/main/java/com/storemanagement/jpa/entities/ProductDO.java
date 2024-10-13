package com.storemanagement.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Table(name = "PRODUCT")
@Entity
@Getter
@SuperBuilder
@RequiredArgsConstructor
public class ProductDO extends AbstractDO {

    private String name;

    private double currentPrice;

    private String description;

    private double rating;

    private long quantity;

    private double weight;

    private boolean archived;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private List<PriceHistoryDO> priceHistoryDOs = new ArrayList<>();

//    add manyToOne to a review table
//    private List<Review> reviews;

}
