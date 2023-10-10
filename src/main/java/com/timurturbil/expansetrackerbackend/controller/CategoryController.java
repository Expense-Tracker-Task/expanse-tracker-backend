package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.Response;
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
    public Response<CategoryDto> findCategoryById(@PathVariable int id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public Response<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
    public Response<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public Response<String> deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public Response<List<CategoryDto>> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
