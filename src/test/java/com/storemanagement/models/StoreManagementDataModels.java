package com.storemanagement.models;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.dtos.ProductDTO;

import java.time.LocalDateTime;
import java.util.List;

public final class StoreManagementDataModels {

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

    public static ProductDO createProductDO(long id) {
        final LocalDateTime date = LocalDateTime.now();

        return ProductDO.builder()
                .id(id)
                .name("Test Product")
                .currentPrice(10)
                .description("Test Description")
                .rating(4)
                .quantity(50)
                .weight(150)
                .archived(false)
                .priceHistoryDOs(List.of(createPriceHistoryDO(1, 10, 1)))
                .createdOn(date)
                .updatedOn(date)
                .build();
    }

    public static ProductDTO createProductDTO(long id) {
        final LocalDateTime date = LocalDateTime.now();

        return ProductDTO.builder()
                .id(id)
                .name("Test Product")
                .currentPrice(10)
                .description("Test Description")
                .rating(4)
                .quantity(50)
                .weight(150)
                .archived(false)
                .priceHistoryList(List.of(createPriceHistoryDTO(1L, 10, 1)))
                .createdOn(date)
                .updatedOn(date)
                .build();
    }
}
