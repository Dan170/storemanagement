package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.jpa.repositories.ProductRepository;
import com.storemanagement.service.dtos.ProductDTO;
import com.storemanagement.service.mappers.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

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
        return productMapper.mapDoToDto(productDO.get());
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Iterable<ProductDO> iterableProductDOs = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (ProductDO productDO : iterableProductDOs) {
            productDTOs.add(productMapper.mapDoToDto(productDO));
        }
        return productDTOs;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductDO savedProductDO = productRepository.save(productMapper.mapDtoToDo(productDTO));
        return productMapper.mapDoToDto(savedProductDO);
    }
}
