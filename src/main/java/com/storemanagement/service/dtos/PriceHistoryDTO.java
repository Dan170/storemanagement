package com.storemanagement.service.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class PriceHistoryDTO {

    private long id;
    private double price;
    @JsonIgnore long productId;
    @JsonIgnore private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
