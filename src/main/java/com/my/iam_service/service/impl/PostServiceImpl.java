package com.my.iam_service.service.impl;

import com.my.iam_service.mapper.PostMapper;
import com.my.iam_service.model.constants.ApiErrorMessage;
import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.entity.Post;
import com.my.iam_service.model.exception.NotFoundException;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.repositories.PostRepository;
import com.my.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository,
                           PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public IamResponse<PostDTO> getPostById(@NotNull Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }
}
