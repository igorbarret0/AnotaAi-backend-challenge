package com.igorbarreto.anotaaichallenge.controller;

import com.igorbarreto.anotaaichallenge.domain.category.Category;
import com.igorbarreto.anotaaichallenge.domain.category.CategoryDTO;
import com.igorbarreto.anotaaichallenge.service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;

    private CategoryController(CategoryService categoryService) {
        this.service = categoryService;
    }
    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData) {

        var newCategory = this.service.insert(categoryData);

        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {

        List<Category> categories = this.service.getAll();

        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData) {

        Category updatedCategory = this.service.update(id, categoryData);

        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {

       this.service.delete(id);

        return ResponseEntity.noContent().build();

    }

}
