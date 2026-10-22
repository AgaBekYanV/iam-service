package com.my.iam_service.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "created", updatable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "updated", updatable = false)
    private LocalDateTime update = LocalDateTime.now();

    @Column(name = "likes", nullable = false, columnDefinition = "integer default 0")
    private Integer likes = 0;
}
