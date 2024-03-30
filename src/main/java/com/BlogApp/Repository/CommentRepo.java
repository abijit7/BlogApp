package com.BlogApp.Repository;

import com.BlogApp.entity.Comment;
import com.BlogApp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);
}
