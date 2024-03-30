package com.BlogApp.Repository;

import com.BlogApp.entity.Category;
import com.BlogApp.entity.Post;
import com.BlogApp.entity.User;
import com.BlogApp.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Long> {

    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
    List<Post> findByPostTitleContaining(String title);
}
