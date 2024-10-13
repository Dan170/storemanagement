package com.storemanagement.service.mappers;

import com.storemanagement.jpa.entities.PriceHistoryDO;
import com.storemanagement.service.dtos.PriceHistoryDTO;

import java.util.List;

import static com.storemanagement.utils.ProductUtils.INVALID_ID;
import static java.util.Collections.emptyList;
import static java.util.Objects.isNull;

public class PriceHistoryMapper {

    public List<PriceHistoryDO> mapDtoToDoList(List<PriceHistoryDTO> priceHistoryDTOs) {
        if (isNull(priceHistoryDTOs) || priceHistoryDTOs.isEmpty()) {
            return emptyList();
        }
        return priceHistoryDTOs.stream().map(this::mapDtoToDo).toList();
    }

    public List<PriceHistoryDTO> mapDoToDtoList(List<PriceHistoryDO> priceHistoryDOs) {
        if (isNull(priceHistoryDOs) || priceHistoryDOs.isEmpty()) {
            return emptyList();
        }
        return priceHistoryDOs.stream().map(this::mapDoToDto).toList();
    }

    public PriceHistoryDO mapDtoToDo(PriceHistoryDTO priceHistoryDTO) {
        if (isNull(priceHistoryDTO)) {
            return PriceHistoryDO.builder().id(INVALID_ID).build();
        }

        return PriceHistoryDO.builder()
                .id(priceHistoryDTO.getId())
                .price(priceHistoryDTO.getPrice())
                .productId(priceHistoryDTO.getProductId())
                .createdOn(priceHistoryDTO.getCreatedOn())
                .updatedOn(priceHistoryDTO.getUpdatedOn())
                .build();
    }

    public PriceHistoryDTO mapDoToDto(PriceHistoryDO priceHistoryDO) {
        if (isNull(priceHistoryDO)) {
            return PriceHistoryDTO.builder().id(INVALID_ID).build();
        }

        return PriceHistoryDTO.builder()
                .id(priceHistoryDO.getId())
                .price(priceHistoryDO.getPrice())
                .productId(priceHistoryDO.getProductId())
                .createdOn(priceHistoryDO.getCreatedOn())
                .updatedOn(priceHistoryDO.getUpdatedOn())
                .build();
    }
}
