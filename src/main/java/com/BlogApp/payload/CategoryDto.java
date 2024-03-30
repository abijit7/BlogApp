package com.BlogApp.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotEmpty(message = "categoryTitle should not be null")
    private String categoryTitle;
    @Length(max = 1000 , message = "maximum Description 1000")
    private String categoryDescription;
}
