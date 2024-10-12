package com.storemanagement.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PriceHistoryDTO {

    private long id;
    private double price;
}
