package com.storemanagement.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ProductDTO {

    private long id;

    private String name;

    private double price;

    private String description;

    private double rating;

    private long quantity;

    private double weight;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

//    add manyToOne to a review table
//    private List<Review> reviews;
}
