package com.storemanagement.jpa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Table(name = "PRICE_HISTORY")
@Entity
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class PriceHistoryDO extends AbstractDO {

    private double price;

    @Column(name = "product_id", nullable = false)
    private Long productId;
}
