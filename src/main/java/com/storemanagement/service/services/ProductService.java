package com.storemanagement.service.services;

import com.storemanagement.service.dtos.ProductDTO;

public interface ProductService {

    ProductDTO getById(long id);
}
