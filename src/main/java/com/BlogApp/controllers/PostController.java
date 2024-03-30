package com.BlogApp.controllers;

import com.BlogApp.config.AppConstants;

import com.BlogApp.payload.ApiResponse;
import com.BlogApp.payload.PostDto;
import com.BlogApp.payload.PostResponse;
import com.BlogApp.services.FileService;
import com.BlogApp.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;
    private final FileService fileService;
    @Value("${project.image}")
    private  String path;
@GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNumber" , defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize" , defaultValue = AppConstants.PAGE_SIZE , required = false) Integer pageSize){
        PostResponse getPost = this.postService.getAllPost(pageNumber,pageSize);
    return new ResponseEntity<>(getPost, HttpStatus.OK);
    }
    @GetMapping("/search/title/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword){
    List<PostDto> searchPost = this.postService.searchPost(keyword);
    return  new ResponseEntity<>(searchPost,HttpStatus.OK);
    }
    @GetMapping("/category/id/{id}")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("id") Long categoryId){
    List<PostDto> getByCategory = this.postService.findPostByCategory(categoryId);
    return new ResponseEntity<>(getByCategory,HttpStatus.OK);
    }
    @GetMapping("/user/id/{id}")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("id") Long userId){
        List<PostDto> getByUser = this.postService.findPostByUser(userId);
        return new ResponseEntity<>(getByUser,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<PostDto> getByPostId(@PathVariable("id") Long postId){
        PostDto getPost = this.postService.getPostById(postId);
        return new ResponseEntity<>(getPost, HttpStatus.OK);
    }
    @PostMapping("/add/user-id/{user-id}/category-id/{category-id}")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable("user-id")Long userId,@PathVariable("category-id") Long categoryId){
    PostDto createPost = this.postService.createPost(postDto,userId,categoryId);
    return new ResponseEntity<>(createPost,HttpStatus.CREATED);
    }
    @PutMapping("/update/id/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable("id") Long postId){
    PostDto updatePost = this.postService.updatePost(postId,postDto);
    return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Long postId){
    this.postService.deletePostById(postId);
    return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true),HttpStatus.OK);
    }
    @PostMapping("/image/upload/id/{id}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile multipartFile,
                                                         @PathVariable("id") Long postId) throws IOException {
        PostDto postById = this.postService.getPostById(postId);
         String fileName = this.fileService.uploadImage(path, multipartFile);
         postById.setImageUrl(fileName);
         PostDto postDto = this.postService.updatePost(postId,postById);
         return new ResponseEntity<>(postDto,HttpStatus.OK);
    }
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resourse = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resourse,response.getOutputStream());

    }

}
