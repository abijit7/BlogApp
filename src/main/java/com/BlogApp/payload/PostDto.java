package com.BlogApp.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Post title should not be empty")
    private String postTitle;
    @NotEmpty(message = "post content should not be empty")
    private String postContent;
    private String imageUrl;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> commentList = new ArrayList<>();
}

