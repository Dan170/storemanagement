package com.storemanagement.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Table(name = "PRICE_HISTORY")
@Entity
@Getter
@SuperBuilder
@RequiredArgsConstructor
public class PriceHistoryDO extends AbstractDO {

    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDO productDO;

    public PriceHistoryDO(double price) {
        super();
        this.price = price;
    }
}
