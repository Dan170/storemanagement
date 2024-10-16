package com.storemanagement;

import com.storemanagement.service.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.storemanagement.models.StoreManagementDataModels.createProductDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StoreManagementApplicationTests {

    @Autowired
    private ProductService service;

    @Test
    void contextLoads() {
    }

    @Test
    void testProductWorkflow() {
        // save product
        var product = createProductDTO(0);
        product.setPriceHistoryList(null);
        var savedProduct = service.saveProduct(product);

        assertNotNull(savedProduct);
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getDescription(), savedProduct.getDescription());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
        assertEquals(0, savedProduct.getRating());
        assertEquals(1, savedProduct.getPriceHistoryList().size());

        // update product
        final var newName = "Updated Product";
        final var newPrice = 999;
        savedProduct.setName(newName);
        savedProduct.setCurrentPrice(newPrice);
        var updatedProduct = service.updateProduct(savedProduct.getId(), savedProduct);

        assertNotNull(updatedProduct);
        assertEquals(newName, updatedProduct.getName());
        assertEquals(newPrice, updatedProduct.getCurrentPrice());
        assertEquals(2, updatedProduct.getPriceHistoryList().size());

        // update price
        final var newSecondPrice = 745;
        var updatedSecondProduct = service.updatePrice(savedProduct.getId(), newSecondPrice);

        assertNotNull(updatedSecondProduct);
        assertEquals(newSecondPrice, updatedSecondProduct.getCurrentPrice());
        assertEquals(3, updatedSecondProduct.getPriceHistoryList().size());

        // get product
        var dbProduct = service.getById(updatedSecondProduct.getId());
        assertNotNull(dbProduct);
        assertEquals(updatedSecondProduct.getName(), dbProduct.getName());
        assertEquals(updatedSecondProduct.getQuantity(), dbProduct.getQuantity());
        assertEquals(3, dbProduct.getPriceHistoryList().size());
    }
}
