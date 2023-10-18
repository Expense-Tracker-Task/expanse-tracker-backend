package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GenericResponse<CategoryDto> findCategoryById(@PathVariable int id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public GenericResponse<String> deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping(path = "/all", produces = "application/json")
    public GenericResponse<List<CategoryDto>> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
