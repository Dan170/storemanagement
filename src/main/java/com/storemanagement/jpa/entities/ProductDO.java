package com.storemanagement.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "product")
@Entity
public class ProductDO {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private double price;

    private String description;

    private double rating;

    private long quantity;

    private double weight;

//    add manyToOne to a review table
//    private List<Review> reviews;

}
