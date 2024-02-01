package com.igorbarreto.anotaaichallenge.service;


import com.igorbarreto.anotaaichallenge.domain.category.Category;
import com.igorbarreto.anotaaichallenge.domain.category.exceptions.CategoryNotFoundException;
import com.igorbarreto.anotaaichallenge.domain.product.Product;
import com.igorbarreto.anotaaichallenge.domain.product.ProductDTO;
import com.igorbarreto.anotaaichallenge.domain.product.exceptions.ProductNotFoundException;
import com.igorbarreto.anotaaichallenge.repositories.ProductRepository;
import com.igorbarreto.anotaaichallenge.service.aws.AwsSnsService;
import com.igorbarreto.anotaaichallenge.service.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    private final AwsSnsService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, AwsSnsService snsService) {
        this.repository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public Product insert(ProductDTO productData) {

        this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(productData);

        this.repository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.toString()));

        return newProduct;

    }

    public Product update(String id, ProductDTO productData) {

        var product = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);


        if (productData.categoryId() != null) {
            this.categoryService.getById(productData.categoryId())
                    .orElseThrow(CategoryNotFoundException::new);
            product.setCategory(productData.categoryId());
        }

        if (!productData.title().isEmpty()) product.setTitle(productData.title());
        if (!productData.description().isEmpty()) product.setDescription(productData.description());
        if (!(productData.price() == null)) product.setPrice(productData.price());

        this.repository.save(product);

        this.snsService.publish(new MessageDTO(product.toString()));


        return product;

    }

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public void delete(String id) {

        Product foundProduct = this.repository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(foundProduct);

    }



}
