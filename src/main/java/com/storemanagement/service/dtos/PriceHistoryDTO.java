package com.storemanagement.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PriceHistoryDTO {

    private long id;
    private double price;
    @JsonIgnore private ProductDTO productDTO;
    @JsonIgnore private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
