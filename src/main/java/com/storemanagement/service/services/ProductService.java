package com.storemanagement.service.services;

import com.storemanagement.service.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getById(long id);

    List<ProductDTO> getAllProducts();

    ProductDTO saveProduct(ProductDTO productDTO);

    ProductDTO updateProduct(long productId, ProductDTO productDTO);

    ProductDTO updatePrice(long productId, double newPrice);
}
