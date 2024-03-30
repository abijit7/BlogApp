package com.BlogApp.serviceImpl;

import com.BlogApp.Repository.CommentRepo;
import com.BlogApp.Repository.PostRepo;
import com.BlogApp.Repository.UserRepo;
import com.BlogApp.entity.Comment;
import com.BlogApp.entity.Post;
import com.BlogApp.entity.User;
import com.BlogApp.exception.ResourceNotFoundException;
import com.BlogApp.payload.CommentDto;
import com.BlogApp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentImpl implements CommentService {
    private final CommentRepo commentRepo;
    private final PostRepo postRepo;
    private final ModelMapper modelMapper;
    private final UserRepo userRepo;
    @Override
    public List<CommentDto> getComment(Long postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(STR."Post Not Found Of Id\{postId}"));
       return this.commentRepo.findByPost(post).stream().map((comments)->this.modelMapper.map(comments, CommentDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Long commentId) {
Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException(STR."comment not found of\{commentId}"));
this.commentRepo.delete(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto,Long commentId) {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException(STR."comment not found of\{commentId}"));
    comment.setContent(commentDto.getContent());
    Comment updated = this.commentRepo.save(comment);
    return this.modelMapper.map(updated, CommentDto.class);
    }

    @Override
    public CommentDto addComment( CommentDto commentDto,Long postId,Long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException(STR."comment not found of\{userId}"));
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException(STR."comment not found of\{postId}"));
    Comment comment= this.modelMapper.map(commentDto,Comment.class);
    comment.setPost(post);
    comment.setUser(user);
    Comment savedComment = this.commentRepo.save(comment);
    return this.modelMapper.map(savedComment, CommentDto.class);
    }
}
