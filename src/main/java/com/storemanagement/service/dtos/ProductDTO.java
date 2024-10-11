package com.storemanagement.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

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
