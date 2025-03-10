package com.storemanagement.web.controller;

import com.storemanagement.service.dtos.ProductDTO;
import com.storemanagement.service.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to Store Management");
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        var foundProducts = productService.getAllProducts();
        return ResponseEntity.ok(foundProducts);
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE', 'MANAGER')")
    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long productId) {
        var foundProduct = productService.getById(productId);
        return ResponseEntity.ok(foundProduct);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        var savedProduct = productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody ProductDTO productDTO) {
        var updatedProduct = productService.updateProduct(productId, productDTO);
        return ResponseEntity.ok(updatedProduct);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/{productId}")
    public ResponseEntity<ProductDTO> updatePrice(@PathVariable Long productId, @RequestParam double price) {
        var updatedProduct = productService.updatePrice(productId, price);
        return ResponseEntity.ok(updatedProduct);
    }
}
