package com.BlogApp.serviceImpl;

import com.BlogApp.Repository.CategoryRepo;
import com.BlogApp.Repository.PostRepo;
import com.BlogApp.Repository.UserRepo;
import com.BlogApp.entity.Category;
import com.BlogApp.entity.Post;
import com.BlogApp.entity.User;
import com.BlogApp.exception.ResourceNotFoundException;
import com.BlogApp.payload.PostDto;
import com.BlogApp.payload.PostResponse;
import com.BlogApp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Post> pages= this.postRepo.findAll(pageable);
        List<Post> postList= pages.getContent();
         List<PostDto> content = postList.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
         PostResponse postResponse = new PostResponse();
         postResponse.setPostList(content);
         postResponse.setPageNumber(pages.getNumber());
         postResponse.setPageSize(pages.getSize());
         postResponse.setTotalElements(pages.getTotalElements());
         postResponse.setTotalPages(pages.getTotalPages());
         postResponse.setLast(pages.isLast());
         return postResponse;
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(STR."Post Not Found Of Id :\{postId}"));
    return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto createPost(PostDto postDto,Long userId,Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(STR."Post Not Found Of Id :\{categoryId}"));
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(STR."Post Not Found Of Id :\{userId}Id}"));
        Post post = this.modelMapper.map(postDto, Post.class);
        post.setUser(user);
        post.setCategory(category);
        Post saved = this.postRepo.save(post);

        return this.modelMapper.map(saved, PostDto.class);
    }

    @Override
    public void deletePostById(Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(STR."Post Not Found Of Id :\{postId}"));
    this.postRepo.delete(post);
    }


    @Override
    public PostDto updatePost(Long postId, PostDto postDto) {
        Post post = this.postRepo.findById(postId).orElseThrow
                (()->new ResourceNotFoundException(STR."Post Not Found Of Id :\{postId}"));
        post.setPostTitle(postDto.getPostTitle());
        post.setPostContent(postDto.getPostContent());
        post.setImageUrl(postDto.getImageUrl());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public List<PostDto> findPostByUser(Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(STR."User not found Of Id :\{userId}"));
       return this.postRepo.findByUser(user).stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostByCategory(Long categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException(STR."Category not Found Of Id :\{categoryId}"));
    return this.postRepo.findByCategory(category).stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
     return this.postRepo.findByPostTitleContaining(keyword).stream().map((post -> this.modelMapper.map(post, PostDto.class))).collect(Collectors.toList());

    }
}
