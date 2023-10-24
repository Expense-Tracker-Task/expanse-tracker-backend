package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.utils.Constants;
import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final JwtService jwtService;

    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository repository, JwtService jwtService, ModelMapper modelMapper) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.modelMapper = modelMapper;
    }
    public GenericResponse<CategoryDto> findCategoryById(int id){
        try {
            Category categoryData = repository.findById(id);
            CategoryDto categoryDto = modelMapper.map(categoryData, CategoryDto.class);
            return new GenericResponse<>(Constants.SUCCESS, Constants.CATEGORY_FOUND, categoryDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<CategoryDto> saveCategory(CategoryDto categoryDto){
        try {
            Category category = modelMapper.map(categoryDto, Category.class);
            repository.save(category);
            categoryDto.setId(category.getId());
            return new GenericResponse<>(Constants.SUCCESS, Constants.CATEGORY_SAVED, categoryDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<CategoryDto> updateCategory(CategoryDto categoryDto){
        try {
            //GET CATEGORY FROM DB
            Category category = repository.findById((long) categoryDto.getId());
            //UPDATE CATEGORY
            category.setName(categoryDto.getName());
            //SAVE CATEGORY
            repository.save(category);
            categoryDto.setId(category.getId());
            return new GenericResponse<>(Constants.SUCCESS, Constants.CATEGORY_UPDATED, categoryDto);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<String> deleteCategory(int id){
        try {
            repository.deleteById((long) id);
            return new GenericResponse<>(Constants.SUCCESS, Constants.CATEGORY_DELETED, null);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public GenericResponse<List<CategoryDto>> getAllCategories(String bearerToken){
        try {
            int userId = jwtService.extractUserId(bearerToken);
            Iterable<Category> iterableCategories = repository.findAllByUserId((long) userId);
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for(Category category : iterableCategories){
                CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
                categoryDtoList.add(categoryDto);
            }
            return new GenericResponse<>(Constants.SUCCESS, Constants.CATEGORIES_FOUND, categoryDtoList);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }
}
