package com.igorbarreto.anotaaichallenge.controller;

import com.igorbarreto.anotaaichallenge.domain.category.Category;
import com.igorbarreto.anotaaichallenge.domain.category.CategoryDTO;
import com.igorbarreto.anotaaichallenge.domain.product.Product;
import com.igorbarreto.anotaaichallenge.domain.product.ProductDTO;
import com.igorbarreto.anotaaichallenge.service.CategoryService;
import com.igorbarreto.anotaaichallenge.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    private ProductController(ProductService productService) {
        this.service = productService;
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {

        var newProduct = this.service.insert(productData);

        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {

        List<Product> allProducts = this.service.getAll();

        return new ResponseEntity<>(allProducts, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData) {

        var productUpdated = this.service.update(id, productData);

        return new ResponseEntity<>(productUpdated, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {

        this.service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
