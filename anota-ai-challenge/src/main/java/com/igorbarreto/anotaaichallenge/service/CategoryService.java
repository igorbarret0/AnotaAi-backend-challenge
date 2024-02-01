package com.igorbarreto.anotaaichallenge.service;

import com.igorbarreto.anotaaichallenge.domain.category.Category;
import com.igorbarreto.anotaaichallenge.domain.category.CategoryDTO;
import com.igorbarreto.anotaaichallenge.domain.category.exceptions.CategoryNotFoundException;
import com.igorbarreto.anotaaichallenge.repositories.CategoryRepository;
import com.igorbarreto.anotaaichallenge.service.aws.AwsSnsService;
import com.igorbarreto.anotaaichallenge.service.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository repository;

    private AwsSnsService snsService;

    public CategoryService(CategoryRepository categoryRepository, AwsSnsService snsService) {
        this.repository = categoryRepository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData) {

        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);

        this.snsService.publish(new MessageDTO(newCategory.toString()));


        return newCategory;

    }

    public List<Category> getAll() {
        return this.repository.findAll();
    }

    public Category update(String id, CategoryDTO categoryData) {

        Category foundCategory = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) foundCategory.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) foundCategory.setDescription(categoryData.description());

        this.repository.save(foundCategory);

        this.snsService.publish(new MessageDTO(foundCategory.toString()));


        return foundCategory;


    }

    public void delete(String id) {

        Category category = this.repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);

    }

    public Optional<Category> getById(String id) {
        return this.repository.findById(id);
    }

}
