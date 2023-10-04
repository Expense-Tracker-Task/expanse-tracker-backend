package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public CategoryDto findCategoryById(int id){
        Category categoryData = repository.findById(id);
        return new CategoryDto(categoryData.getId(), categoryData.getName());
    }
    public CategoryDto saveCategory(CategoryDto categoryDto){
        Category category = new Category(
                categoryDto.getName()
        );
        category = repository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }
    public CategoryDto updateCategory(int id, CategoryDto categoryDto){
        Category category = repository.findById(id);
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category = repository.save(category);
        categoryDto.setId(category.getId());
        return categoryDto;
    }

    public List<CategoryDto> getAllCategories(){
        Iterable<Category> iterableCategories = repository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        for(Category category : iterableCategories){
            categoryDtos.add(new CategoryDto(
                    category.getId(),
                    category.getName()
            ));
        }
        return categoryDtos;
    }

    public void deleteCategory(int id){
        repository.deleteById(id);
    }
}
