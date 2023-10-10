package com.timurturbil.expansetrackerbackend.service;

import com.timurturbil.expansetrackerbackend.dto.CategoryDto;
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
    public CategoryDto findCategoryById(int id){
        try {
            Category categoryData = repository.findById(id);
            return modelMapper.map(categoryData, CategoryDto.class);
        } catch (Exception e) {
            return null;
        }
    }
    public CategoryDto saveCategory(CategoryDto categoryDto){
        try {
            Category category = modelMapper.map(categoryDto, Category.class);
            repository.save(category);
            return categoryDto;
        } catch (Exception e) {
            return null;
        }
    }

    //TODO: update servisine bakılacak, refactor gerekebilir
    public CategoryDto updateCategory(CategoryDto categoryDto){
        try {
            //GET CATEGORY FROM DB
            Category category = repository.findById((long) categoryDto.getId());
            //UPDATE CATEGORY
            category.setName(categoryDto.getName());
            //SAVE CATEGORY
            repository.save(category);
            return categoryDto;
        } catch (Exception e) {
            return null;
        }
    }

    public List<CategoryDto> getAllCategories(){
        try {
            Iterable<Category> iterableCategories = repository.findAll();
            List<CategoryDto> categoryDtoList = new ArrayList<>();
            for(Category category : iterableCategories){
                categoryDtoList.add(modelMapper.map(category, CategoryDto.class));
            }
            return categoryDtoList;
        } catch (Exception e) {
            return null;
        }
    }

    public void deleteCategory(int id){
        repository.deleteById(id);
    }
}
