package com.my.iam_service.service;

import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.dto.post.PostSearchDTO;
import com.my.iam_service.model.request.post.NewPostRequest;
import com.my.iam_service.model.request.post.UpdatePostRequest;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.model.response.PaginationResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Pageable;


public interface PostService {

    IamResponse<PostDTO> getPostById(@NotNull Integer id);

    IamResponse<PostDTO> createPost(@NotNull NewPostRequest newPostRequest);

    IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull UpdatePostRequest updatePostRequest);

    void softDeletePost(@NotNull Integer postId);

    IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable);
}
