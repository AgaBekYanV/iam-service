package com.my.iam_service.repositories;

import com.my.iam_service.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsByTitle(String title);
}
