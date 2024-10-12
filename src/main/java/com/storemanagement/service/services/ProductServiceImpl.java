package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.jpa.repositories.ProductRepository;
import com.storemanagement.service.dtos.ProductDTO;
import com.storemanagement.service.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = new ProductMapper();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getById(long id) {
        Optional<ProductDO> productDO = productRepository.findById(id);
        if (productDO.isEmpty()) {
            return new ProductDTO();
        }
        return productMapper.mapDoToDTo(productDO.get());
    }
}
