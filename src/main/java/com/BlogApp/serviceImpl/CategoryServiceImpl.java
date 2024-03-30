package com.BlogApp.serviceImpl;

import com.BlogApp.Repository.CategoryRepo;
import com.BlogApp.entity.Category;
import com.BlogApp.payload.CategoryDto;
import com.BlogApp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.BlogApp.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAllCategory() {
        return this.categoryRepo.findAll().stream().map((category) ->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(STR."Category Not Found Of Id\{categoryId}"));
    category.setCategoryTitle(categoryDto.getCategoryTitle());
    category.setCategoryDescription(categoryDto.getCategoryDescription());
    Category updatedCategory = this.categoryRepo.save(category);
    return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(STR."Category Not Found Of Id\{categoryId}"));
    return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(STR."Category Not Found Of Id\{categoryId}"));
  this.categoryRepo.delete(category);

    }
}
