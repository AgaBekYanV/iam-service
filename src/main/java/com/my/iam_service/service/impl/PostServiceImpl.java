package com.my.iam_service.service.impl;

import com.my.iam_service.model.constants.ApiErrorMessage;
import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.enteties.Post;
import com.my.iam_service.model.exception.NotFoundException;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.repositories.PostRepository;
import com.my.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public IamResponse<PostDTO> getPostById(@NotNull Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        PostDTO postDTO = PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likes(post.getLikes())
                .created(post.getCreated())
                .build();
        return IamResponse.createSuccessful(postDTO);
    }
}
