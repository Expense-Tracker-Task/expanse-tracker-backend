package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public CategoryDto findCategoryById(@PathVariable int id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public CategoryDto saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public CategoryDto updateCategory(@PathVariable int id, @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
