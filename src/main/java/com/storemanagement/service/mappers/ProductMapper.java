package com.storemanagement.service.mappers;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.service.dtos.ProductDTO;

public class ProductMapper {

    public ProductDO mapDtoToDo(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }

        return ProductDO.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .currentPrice(productDTO.getPrice())
                .description(productDTO.getDescription())
                .rating(productDTO.getRating())
                .quantity(productDTO.getQuantity())
                .weight(productDTO.getWeight())
                .build();
    }

    public ProductDTO mapDoToDTo(ProductDO productDO) {
        if (productDO == null) {
            return null;
        }

        return ProductDTO.builder()
                .id(productDO.getId())
                .name(productDO.getName())
                .price(productDO.getCurrentPrice())
                .description(productDO.getDescription())
                .rating(productDO.getRating())
                .quantity(productDO.getQuantity())
                .weight(productDO.getWeight())
                .build();
    }
}
