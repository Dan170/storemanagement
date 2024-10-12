package com.storemanagement.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Table(name = "price_history")
@Entity
@Getter
@SuperBuilder
public class PriceHistory extends AbstractDO {

    private double price;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductDO productDO;

    public PriceHistory(double price) {
        super();
        this.price = price;
    }
}
