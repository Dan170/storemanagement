package com.storemanagement.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private long id;
    private String name;
    private double currentPrice;
    private String description;
    private double rating;
    private long quantity;
    private double weight;
    private boolean archived;
    private List<PriceHistoryDTO> priceHistoryList;
    @JsonIgnore private LocalDateTime createdOn;
    @JsonIgnore private LocalDateTime updatedOn;
}
