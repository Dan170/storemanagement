package com.storemanagement.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @OneToMany(mappedBy = "productDO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistoryDO> priceHistoryDOs;

    @PrePersist
    protected void onCreate() {
        super.onCreate();
        this.rating = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        super.onUpdate();
        PriceHistoryDO oldPrice = new PriceHistoryDO(this.currentPrice);
        this.priceHistoryDOs.add(oldPrice);
    }

//    add manyToOne to a review table
//    private List<Review> reviews;

}
