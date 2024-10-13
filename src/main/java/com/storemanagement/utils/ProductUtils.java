package com.storemanagement.utils;

import com.storemanagement.service.dtos.ProductDTO;

public final class ProductUtils {

    public final static long INVALID_ID = -1;

    public static boolean hasInvalidId(ProductDTO productDTO) {
        return productDTO.getId() == INVALID_ID;
    }
}
