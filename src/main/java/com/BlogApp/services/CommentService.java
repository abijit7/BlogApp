package com.BlogApp.services;

import com.BlogApp.payload.CommentDto;

import java.util.List;

public interface CommentService {
List<CommentDto> getComment(Long postId);
void deleteComment(Long commentId);
CommentDto updateComment(CommentDto commentDto,Long commentId);
CommentDto addComment(CommentDto commentDto,Long postId,Long userId);

}
