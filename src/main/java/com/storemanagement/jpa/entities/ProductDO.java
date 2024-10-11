package com.storemanagement.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Table(name = "product")
@Entity
@Getter
@SuperBuilder
public class ProductDO extends AbstractDO {

    private String name;

    private double price;

    private String description;

    private double rating;

    private long quantity;

    private double weight;

    @PrePersist
    protected void onCreate() {
        super.onCreate();
        this.rating = 0.0;
    }

//    add manyToOne to a review table
//    private List<Review> reviews;

}
