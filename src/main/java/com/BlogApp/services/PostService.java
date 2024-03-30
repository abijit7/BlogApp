package com.BlogApp.services;

import com.BlogApp.payload.PostDto;
import com.BlogApp.payload.PostResponse;

import java.awt.print.Pageable;
import java.util.List;

public interface PostService {

    PostResponse getAllPost(Integer pageNumber, Integer pageSize);
    PostDto getPostById(Long postId);
    PostDto createPost(PostDto postDto,Long userId,Long categoryId);
    void deletePostById(Long postId);
    PostDto updatePost(Long postId,PostDto postDto);
    List<PostDto> findPostByUser(Long userId);
    List<PostDto> findPostByCategory(Long categoryId);
    List<PostDto> searchPost(String keyboard);
}
