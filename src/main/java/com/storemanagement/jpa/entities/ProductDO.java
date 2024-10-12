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

    @OneToMany(mappedBy = "productDO", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PriceHistoryDO> priceHistoryDOs = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        super.onCreate();

        PriceHistoryDO oldPrice = new PriceHistoryDO(this.currentPrice, this);
        this.priceHistoryDOs.add(oldPrice);
    }

    @PreUpdate
    protected void onUpdate() {
        super.onUpdate();

        if (this.priceHistoryDOs == null || this.priceHistoryDOs.isEmpty()) {
            PriceHistoryDO priceHistory = new PriceHistoryDO(this.currentPrice, this);
            this.priceHistoryDOs.add(priceHistory);
        } else {
            PriceHistoryDO lastHistory = this.priceHistoryDOs.get(this.priceHistoryDOs.size() - 1);
            if (lastHistory.getPrice() != this.currentPrice) {
                PriceHistoryDO priceHistory = new PriceHistoryDO(this.currentPrice, this);
                this.priceHistoryDOs.add(priceHistory);
            }
        }
    }

//    add manyToOne to a review table
//    private List<Review> reviews;

}
