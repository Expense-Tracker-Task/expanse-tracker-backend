package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.Constants;
import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
import com.timurturbil.expansetrackerbackend.dto.Response;
import com.timurturbil.expansetrackerbackend.entity.Category;
import com.timurturbil.expansetrackerbackend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }
    public Response<CategoryDto> findCategoryById(int id){
        try {
            Category categoryData = repository.findById(id);
            CategoryDto categoryDto = modelMapper.map(categoryData, CategoryDto.class);
            return new Response<>(Constants.SUCCESS, Constants.CATEGORY_FOUND, categoryDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public Response<CategoryDto> saveCategory(CategoryDto categoryDto){
        try {
            Category category = modelMapper.map(categoryDto, Category.class);
            repository.save(category);
            categoryDto.setId(category.getId());
            return new Response<>(Constants.SUCCESS, Constants.CATEGORY_SAVED, categoryDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public Response<CategoryDto> updateCategory(CategoryDto categoryDto){
        try {
            //GET CATEGORY FROM DB
            Category category = repository.findById((long) categoryDto.getId());
            //UPDATE CATEGORY
            category.setName(categoryDto.getName());
            //SAVE CATEGORY
            repository.save(category);
            categoryDto.setId(category.getId());
            return new Response<>(Constants.SUCCESS, Constants.CATEGORY_UPDATED, categoryDto);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public Response<String> deleteCategory(int id){
        try {
            repository.deleteById((long) id);
            return new Response<>(Constants.SUCCESS, Constants.CATEGORY_DELETED, null);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
    public Response<List<CategoryDto>> getAllCategories(){
        try {
            Iterable<Category> iterableCategories = repository.findAll();
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for(Category category : iterableCategories){
                categoryDtoList.add(modelMapper.map(category, CategoryDto.class));
            }
            return new Response<>(Constants.SUCCESS, Constants.CATEGORIES_FOUND, categoryDtoList);
        } catch (Exception e) {
            return new Response<>(Constants.ERROR, e.getMessage(), null);
        }
    }
}
