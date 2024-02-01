package com.igorbarreto.anotaaichallenge.repositories;

import com.igorbarreto.anotaaichallenge.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
