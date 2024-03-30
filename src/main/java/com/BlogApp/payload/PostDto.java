package com.BlogApp.payload;

import com.BlogApp.entity.Category;
import com.BlogApp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String postTitle;
    private String postContent;
    private String imageUrl;
    private UserDto user;
    private CategoryDto category;
    private List<CommentDto> commentList;
}

