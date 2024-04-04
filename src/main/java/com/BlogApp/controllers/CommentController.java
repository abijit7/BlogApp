package com.BlogApp.controllers;

import com.BlogApp.payload.ApiResponse;
import com.BlogApp.payload.CommentDto;
import com.BlogApp.services.CommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comment")
public class CommentController {
    private final CommentService commentService;
    @GetMapping("/postId/{postId}")
    public ResponseEntity<List<CommentDto>> getByPostId(@PathVariable("postId") Long postId){
        List<CommentDto> commentDto = this.commentService.getComment(postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @PostMapping("/add/comment/user/{user-id}/post/{post-id}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("post-id") Long postId,@PathVariable("user-id") Long userId){
        CommentDto commentDto1 = this.commentService.addComment(commentDto,postId,userId);
        return new ResponseEntity<>(commentDto1,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/comment-id/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Deleted Successfully",true),HttpStatus.OK);
    }
}
