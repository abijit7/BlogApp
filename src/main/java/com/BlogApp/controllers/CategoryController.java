package com.BlogApp.controllers;

import com.BlogApp.payload.ApiResponse;
import com.BlogApp.payload.CategoryDto;
import com.BlogApp.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "Category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategory (){
        List<CategoryDto> categoryDto = this.categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<CategoryDto> createCategory (@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }
    @PutMapping("/update/id/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                      @PathVariable("id") Long categoryId){
        CategoryDto categoryDto1 = this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        CategoryDto categoryDto = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/id/{id}")
     public  ResponseEntity<ApiResponse> deleteCategoryById(@PathVariable("id") Long categoryId){
        this.categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
     }
}
