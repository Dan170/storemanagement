package com.storemanagement.service.mappers;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.dtos.ProductDTO;

import java.util.List;

import static java.util.Objects.isNull;

public class ProductMapper {

    private final PriceHistoryMapper priceHistoryMapper = new PriceHistoryMapper();

    public List<ProductDO> mapDtoToDo(List<ProductDTO> productDTOs) {
        return productDTOs.stream().map(this::mapDtoToDo).toList();
    }

    public List<ProductDTO> mapDoToDto(List<ProductDO> productDOs) {
        return productDOs.stream().map(this::mapDoToDto).toList();
    }

    public ProductDO mapDtoToDo(ProductDTO productDTO) {
        if (isNull(productDTO)) {
            return null;
        }

        return ProductDO.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .currentPrice(productDTO.getCurrentPrice())
                .description(productDTO.getDescription())
                .rating(productDTO.getRating())
                .quantity(productDTO.getQuantity())
                .weight(productDTO.getWeight())
                .build();
    }

    public ProductDTO mapDoToDto(ProductDO productDO) {
        if (isNull(productDO)) {
            return new ProductDTO();
        }

        List<PriceHistoryDTO> priceHistoryDTOs = priceHistoryMapper.mapDoToDto(productDO.getPriceHistoryDOs());

        return ProductDTO.builder()
                .id(productDO.getId())
                .name(productDO.getName())
                .currentPrice(productDO.getCurrentPrice())
                .description(productDO.getDescription())
                .rating(productDO.getRating())
                .quantity(productDO.getQuantity())
                .weight(productDO.getWeight())
                .priceHistoryList(priceHistoryDTOs)
                .createdOn(productDO.getCreatedOn())
                .updatedOn(productDO.getUpdatedOn())
                .build();
    }
}
