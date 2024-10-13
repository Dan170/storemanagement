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

import static com.storemanagement.utils.ProductUtils.INVALID_ID;

@Service
@Transactional
class ProductServiceImpl implements ProductService {

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
    public ProductDTO updateProduct(long productId, ProductDTO productWithUpdates) {
        ProductDTO existingProduct = getById(productId);
        if (existingProduct.getId() == INVALID_ID) {
            return ProductDTO.builder().id(INVALID_ID).build();
        }

        return updateProductAndHistory(existingProduct, productWithUpdates);
    }

    @Override
    public ProductDTO updatePrice(long productId, double newPrice) {
        ProductDTO existingProduct = getById(productId);
        if (existingProduct.getId() == INVALID_ID) {
            return ProductDTO.builder().id(INVALID_ID).build();
        }

        ProductDTO productWithNewPrice = productMapper.mapDtoToDto(existingProduct);
        productWithNewPrice.setCurrentPrice(newPrice);
        return updateProductAndHistory(existingProduct, productWithNewPrice);
    }

    private ProductDTO updateProductAndHistory(ProductDTO existingProduct, ProductDTO productWithUpdates) {
        List<PriceHistoryDTO> priceHistory = updatePriceHistory(existingProduct, productWithUpdates.getCurrentPrice());

        productWithUpdates.setId(existingProduct.getId());
        productWithUpdates.setPriceHistoryList(priceHistory);
        productWithUpdates.setCreatedOn(existingProduct.getCreatedOn());
        ProductDO savedProductDO = productRepository.save(productMapper.mapDtoToDo(productWithUpdates));
        return productMapper.mapDoToDto(savedProductDO);
    }

    private List<PriceHistoryDTO> updatePriceHistory(ProductDTO existingProduct, double newPrice) {
        List<PriceHistoryDTO> priceHistory = new ArrayList<>(existingProduct.getPriceHistoryList());
        if (newPrice != existingProduct.getCurrentPrice()) {
            PriceHistoryDTO savedPriceHistory = savePriceHistory(newPrice, existingProduct.getId());
            priceHistory.add(savedPriceHistory);
        }
        return priceHistory;
    }

    private PriceHistoryDTO savePriceHistory(double currentPrice, long productId) {
        PriceHistoryDTO priceHistoryDTO = PriceHistoryDTO.builder()
                .price(currentPrice)
                .productId(productId)
                .build();
        return priceHistoryService.savePriceHistory(priceHistoryDTO);
    }
}
