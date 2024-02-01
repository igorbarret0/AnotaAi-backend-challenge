package com.igorbarreto.anotaaichallenge.domain.product;

import com.igorbarreto.anotaaichallenge.domain.category.Category;

public record ProductDTO(
        String title,
        String description,
        String ownerId,
        Integer price,
        String categoryId
) {
}
