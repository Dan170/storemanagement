package com.storemanagement.service.services;

import com.storemanagement.jpa.entities.ProductDO;
import com.storemanagement.jpa.repositories.ProductRepository;
import com.storemanagement.service.dtos.PriceHistoryDTO;
import com.storemanagement.service.dtos.ProductDTO;
import com.storemanagement.exceptionhandling.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDO;
import static com.storemanagement.models.StoreManagementDataModels.createPriceHistoryDTO;
import static com.storemanagement.models.StoreManagementDataModels.createProductDO;
import static com.storemanagement.models.StoreManagementDataModels.createProductDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceUnitTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final PriceHistoryService priceHistoryService = mock(PriceHistoryService.class);

    private final ProductService productService = new ProductServiceImpl(productRepository, priceHistoryService);

    @Test
    void testGetById() {
        // given
        final long productId = 1;
        var expectedProductDO = createProductDO(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(expectedProductDO));

        // when
        var actualProductDTO = productService.getById(productId);

        // then
        assertEquals(expectedProductDO.getId(), actualProductDTO.getId());
        assertEquals(expectedProductDO.getName(), actualProductDTO.getName());
        assertEquals(expectedProductDO.getCurrentPrice(), actualProductDTO.getCurrentPrice());
        assertEquals(expectedProductDO.getPriceHistoryDOs().size(), actualProductDTO.getPriceHistoryList().size());
    }

    @Test
    void testGetById_InvalidId() {
        // given
        final long productId = -99;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.getById(productId));

        // then
        assertProductNotFoundEx(exception, productId);
    }

    @Test
    void testGetAllProducts() {
        // given
        final long id1 = 1;
        final long id2 = 12;
        final long id3 = 123;
        List<ProductDO> expectedProductDOs = List.of(createProductDO(id1), createProductDO(id2), createProductDO(id3));
        when(productRepository.findAll()).thenReturn(expectedProductDOs);

        // when
        List<ProductDTO> actualProductDTOs = productService.getAllProducts();

        // then
        assertEquals(expectedProductDOs.size(), actualProductDTOs.size());
        assertEquals(expectedProductDOs.get(0).getId(), actualProductDTOs.get(0).getId());
    }

    @Test
    void testSaveProduct() {
        // given
        final long id = 1;
        var expectedProductDO = createProductDO(id);
        var expectedProductDTO = createProductDTO(id);
        var productDToToSave = createProductDTO(0);
        productDToToSave.setCreatedOn(null);
        productDToToSave.setUpdatedOn(null);
        when(productRepository.save(any(ProductDO.class))).thenReturn(expectedProductDO);

        var savedPriceHistory = createPriceHistoryDTO(1L, expectedProductDO.getCurrentPrice(), expectedProductDO.getId());
        when(priceHistoryService.savePriceHistory(any(PriceHistoryDTO.class))).thenReturn(savedPriceHistory);

        // when
        var actualProductDTO = productService.saveProduct(productDToToSave);

        // then
        assertEquals(expectedProductDTO.getId(), actualProductDTO.getId());
        assertEquals(expectedProductDTO.getName(), actualProductDTO.getName());
        assertEquals(expectedProductDTO.getCurrentPrice(), actualProductDTO.getCurrentPrice());
        assertEquals(expectedProductDTO.getPriceHistoryList().size(), actualProductDTO.getPriceHistoryList().size());
    }

    @Test
    void testUpdateProduct() {
        // given
        final long productId = 1;
        var expectedProductDTO = createProductDTO(productId);
        var expectedProductDO = createProductDO(productId);
        var existingProductDO = createProductDO(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProductDO));
        when(productRepository.save(any())).thenReturn(expectedProductDO);

        // when
        var actualProductDTO = productService.updateProduct(productId, expectedProductDTO);

        // then
        assertEquals(expectedProductDTO.getId(), actualProductDTO.getId());
        assertEquals(expectedProductDTO.getName(), actualProductDTO.getName());
        assertEquals(expectedProductDTO.getCurrentPrice(), actualProductDTO.getCurrentPrice());
        assertEquals(expectedProductDTO.getPriceHistoryList().size(), actualProductDTO.getPriceHistoryList().size());
    }

    @Test
    void testUpdateProduct_InvalidId() {
        // given
        final long productId = -99;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.updateProduct(productId, createProductDTO(productId)));

        // then
        assertProductNotFoundEx(exception, productId);
    }

    @Test
    void testUpdatePrice() {
        // given
        final long productId = 1;
        final double newPrice = 50;
        var expectedProductDO = createProductDO(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.ofNullable(expectedProductDO));

        var expectedProductDTO = createProductDTO(productId);
        expectedProductDTO.setCurrentPrice(newPrice);
        var newPriceHistoryEntry = createPriceHistoryDTO(2, newPrice, productId);
        when(priceHistoryService.savePriceHistory(any())).thenReturn(newPriceHistoryEntry);

        var existingPriceHistory = new ArrayList<>(expectedProductDTO.getPriceHistoryList());
        existingPriceHistory.add(newPriceHistoryEntry);
        expectedProductDTO.setPriceHistoryList(existingPriceHistory);

        var updatedProductDO = createProductDO(productId);
        updatedProductDO.setCurrentPrice(newPrice);
        var newPriceHistory = createPriceHistoryDO(2, newPrice, productId);
        var existingPriceHistoryDOs = new ArrayList<>(updatedProductDO.getPriceHistoryDOs());
        existingPriceHistoryDOs.add(newPriceHistory);
        updatedProductDO.setPriceHistoryDOs(existingPriceHistoryDOs);
        when(productRepository.save(any())).thenReturn(updatedProductDO);

        // when
        var actualProductDTO = productService.updatePrice(productId, newPrice);

        // then
        assertEquals(expectedProductDTO.getId(), actualProductDTO.getId());
        assertEquals(expectedProductDTO.getName(), actualProductDTO.getName());
        assertEquals(expectedProductDTO.getCurrentPrice(), actualProductDTO.getCurrentPrice());
        assertEquals(expectedProductDTO.getPriceHistoryList().size(), actualProductDTO.getPriceHistoryList().size());
    }

    @Test
    void updatePrice_InvalidId() {
        // given
        final long productId = -98;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.updatePrice(productId, 10));

        // then
        assertProductNotFoundEx(exception, productId);
    }

    private void assertProductNotFoundEx(ResourceNotFoundException exception, long productId) {
        assertNotNull(exception);
        assertEquals("Product with id [" + productId + "] not found", exception.getMessage());
    }
}
