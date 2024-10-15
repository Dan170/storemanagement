package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.jpa.repositories.ProductRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.dtos.ProductDTO;
import com.storemanagement.service.mappers.ProductMapper;
import com.storemanagement.exceptionhandling.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

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
        return productRepository.findById(id)
                .map(product -> {
                    var savedProduct = productMapper.mapDoToDto(product);
                    LOGGER.info("Retrieved Product with id [{}]", id);
                    return savedProduct;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Product with id [" + id + "] not found"));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        Iterable<ProductDO> iterableProductDOs = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (var productDO : iterableProductDOs) {
            productDTOs.add(productMapper.mapDoToDto(productDO));
        }
        LOGGER.info("Retrieved Products with ids {}",
                productDTOs.stream().map(ProductDTO::getId).toList());
        return productDTOs;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        productDTO.setId(0);
        var savedProductDO = productRepository.save(productMapper.mapDtoToDo(productDTO));
        var savedProductDTO = productMapper.mapDoToDto(savedProductDO);
        var savedPriceHistory = savePriceHistory(productDTO.getCurrentPrice(), savedProductDTO.getId());
        savedProductDTO.setPriceHistoryList(List.of(savedPriceHistory));

        LOGGER.info("Saved Product with id [{}]", savedProductDTO.getId());
        return savedProductDTO;
    }

    @Override
    public ProductDTO updateProduct(long productId, ProductDTO productWithUpdates) {
        var existingProduct = getById(productId);
        return updateProductAndHistory(existingProduct, productWithUpdates);
    }

    @Override
    public ProductDTO updatePrice(long productId, double newPrice) {
        var existingProduct = getById(productId);
        var productWithNewPrice = productMapper.mapDtoToDto(existingProduct);
        productWithNewPrice.setCurrentPrice(newPrice);
        return updateProductAndHistory(existingProduct, productWithNewPrice);
    }

    private ProductDTO updateProductAndHistory(ProductDTO existingProduct, ProductDTO productWithUpdates) {
        LOGGER.info("Updating Product with id [{}]", existingProduct.getId());
        List<PriceHistoryDTO> priceHistory = updatePriceHistory(existingProduct, productWithUpdates.getCurrentPrice());

        productWithUpdates.setId(existingProduct.getId());
        productWithUpdates.setPriceHistoryList(priceHistory);
        productWithUpdates.setCreatedOn(existingProduct.getCreatedOn());
        var savedProductDO = productRepository.save(productMapper.mapDtoToDo(productWithUpdates));
        LOGGER.info("Updated Product with id [{}]", savedProductDO.getId());
        return productMapper.mapDoToDto(savedProductDO);
    }

    private List<PriceHistoryDTO> updatePriceHistory(ProductDTO existingProduct, double newPrice) {
        List<PriceHistoryDTO> priceHistory = new ArrayList<>(existingProduct.getPriceHistoryList());

        if (newPrice == existingProduct.getCurrentPrice()) {
            LOGGER.warn("Old price is equal to new price, will not update Price History");
            return priceHistory;
        }
        var savedPriceHistory = savePriceHistory(newPrice, existingProduct.getId());
        priceHistory.add(savedPriceHistory);
        return priceHistory;
    }

    private PriceHistoryDTO savePriceHistory(double currentPrice, long productId) {
        var priceHistoryDTO = PriceHistoryDTO.builder()
                .price(currentPrice)
                .productId(productId)
                .build();
        return priceHistoryService.savePriceHistory(priceHistoryDTO);
    }
}
