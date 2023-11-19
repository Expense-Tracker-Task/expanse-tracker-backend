package com.timurturbil.expansetrackerbackend.controller;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping(path = "/{id}", produces = "application/json")
    public GenericResponse<CategoryDto> findCategoryById(@PathVariable int id) {
        return categoryService.findCategoryById(id);
    }

    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public GenericResponse<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.saveCategory(categoryDto);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public GenericResponse<CategoryDto> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDto categoryDto) {
        return categoryService.updateCategory(id, categoryDto);
    }

    @DeleteMapping(path = "/{id}")
    public GenericResponse<String> deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @GetMapping(path = "", produces = "application/json")
    public GenericResponse<List<CategoryDto>> getAllCategories(@RequestHeader("Authorization") String bearerToken){
        return categoryService.getAllCategories(bearerToken);
    }
}
