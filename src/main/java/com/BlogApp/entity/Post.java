package com.BlogApp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post-table")
public class Post {
    @Column(name = "post_id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "post_sequence")
    @SequenceGenerator(name = "post_sequence",sequenceName = "post_sequence",allocationSize = 1)
    private Long id;
    private String postTitle;
    private String postContent;
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
   private Category category;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
   private List<Comment> comment;
}
