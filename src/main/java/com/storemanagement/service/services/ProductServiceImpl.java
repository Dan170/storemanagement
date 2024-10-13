package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.jpa.repositories.ProductRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
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

    private final static long INVALID_ID = -1;

    private final ProductRepository productRepository;
    private final PriceHistoryService priceHistoryService;
    private final ProductMapper productMapper = new ProductMapper();

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, PriceHistoryService priceHistoryService) {
        this.productRepository = productRepository;
        this.priceHistoryService = priceHistoryService;
    }

    @Override
    public ProductDTO getById(long id) {
        Optional<ProductDO> productDO = productRepository.findById(id);
        if (productDO.isEmpty()) {
            return ProductDTO.builder().id(INVALID_ID).build();
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
        productDTO.setId(0);
        ProductDO savedProductDO = productRepository.save(productMapper.mapDtoToDo(productDTO));
        ProductDTO savedProductDTO = productMapper.mapDoToDto(savedProductDO);

        PriceHistoryDTO savedPriceHistory = savePriceHistory(productDTO.getCurrentPrice(), savedProductDTO.getId());
        savedProductDTO.setPriceHistoryList(List.of(savedPriceHistory));

        return savedProductDTO;
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO productToUpdate) {
        ProductDTO existingProduct = getById(productId);
        if (existingProduct.getId() == INVALID_ID) {
            return ProductDTO.builder().id(INVALID_ID).build();
        }

        List<PriceHistoryDTO> priceHistory = new ArrayList<>(existingProduct.getPriceHistoryList());
        if (productToUpdate.getCurrentPrice() != existingProduct.getCurrentPrice()) {
            PriceHistoryDTO savedPriceHistory = savePriceHistory(productToUpdate.getCurrentPrice(), productId);
            priceHistory.add(savedPriceHistory);
        }

        productToUpdate.setId(productId);
        productToUpdate.setPriceHistoryList(priceHistory);
        productToUpdate.setCreatedOn(existingProduct.getCreatedOn());
        ProductDO savedProductDO = productRepository.save(productMapper.mapDtoToDo(productToUpdate));
        return productMapper.mapDoToDto(savedProductDO);
    }

    private PriceHistoryDTO savePriceHistory(double currentPrice, long productId) {
        PriceHistoryDTO priceHistoryDTO = PriceHistoryDTO.builder()
                .price(currentPrice)
                .productId(productId)
                .build();
        return priceHistoryService.savePriceHistory(priceHistoryDTO);
    }
}
