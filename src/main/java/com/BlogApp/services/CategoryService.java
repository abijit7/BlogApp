package com.BlogApp.services;

import com.BlogApp.payload.CategoryDto;

import java.util.List;

public interface CategoryService {


    List<CategoryDto> getAllCategory();

    CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    CategoryDto getCategoryById(Long categoryId);

    void deleteCategoryById(Long categoryId);
}
