package com.BlogApp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category-table")
public class Category {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE
        ,generator = "category-sequence")
@SequenceGenerator(name = "category-sequence"
        ,sequenceName = "category-sequence",allocationSize = 1)
@Column(name = "category_id")
    private Long id;
@Column(nullable = false)
    private String categoryTitle;
    private String categoryDescription;
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Post> posts;

}
