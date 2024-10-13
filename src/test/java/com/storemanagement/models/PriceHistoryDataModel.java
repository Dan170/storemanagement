package com.storemanagement.models;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import com.storemanagement.service.dtos.PriceHistoryDTO;

import java.time.LocalDateTime;

public final class PriceHistoryDataModel {

    public static PriceHistoryDO createPriceHistoryDO(long id, double price, long productId) {
        final LocalDateTime date = LocalDateTime.now();
        return PriceHistoryDO.builder()
                .id(id)
                .price(price)
                .productId(productId)
                .createdOn(date)
                .updatedOn(date)
                .build();
    }

    public static PriceHistoryDTO createPriceHistoryDTO(long id, double price, long productId) {
        final LocalDateTime date = LocalDateTime.now();
        return PriceHistoryDTO.builder()
                .id(id)
                .price(price)
                .productId(productId)
                .createdOn(date)
                .updatedOn(date)
                .build();
    }
}
