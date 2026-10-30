package com.my.iam_service.service.impl;

import com.my.iam_service.mapper.PostMapper;
import com.my.iam_service.model.constants.ApiErrorMessage;
import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.dto.post.PostSearchDTO;
import com.my.iam_service.model.entity.Post;
import com.my.iam_service.model.exception.DataExistException;
import com.my.iam_service.model.exception.NotFoundException;
import com.my.iam_service.model.request.post.NewPostRequest;
import com.my.iam_service.model.request.post.UpdatePostRequest;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.model.response.PaginationResponse;
import com.my.iam_service.repositories.PostRepository;
import com.my.iam_service.service.PostService;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

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
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        PostDTO postDTO = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDTO);
    }

    @Override
    public IamResponse<PostDTO> createPost(NewPostRequest newPostRequest) {
        if(postRepository.existsByTitle(newPostRequest.getTitle())) {
            throw new DataExistException(ApiErrorMessage.POST_ALREADY_EXISTS.getMessage(newPostRequest.getTitle()));
        }
        Post post = postMapper.createPost(newPostRequest);
        Post savedPost = postRepository.save(post);

        PostDTO postDto = postMapper.toPostDTO(savedPost);
        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public IamResponse<PostDTO> updatePost(@NotNull Integer postId, @NotNull UpdatePostRequest request) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        postMapper.updatePost(post, request);
        post.setUpdate(LocalDateTime.now());
        post = postRepository.save(post);

        PostDTO postDto = postMapper.toPostDTO(post);
        return IamResponse.createSuccessful(postDto);
    }

    @Override
    public void softDeletePost(@NotNull Integer postId) {
        Post post = postRepository.findByIdAndDeletedFalse(postId)
                .orElseThrow(()-> new NotFoundException(ApiErrorMessage.POST_NOT_FOUND_BY_ID.getMessage(postId)));
        post.setDeleted(true);
        postRepository.save(post);
    }

    @Override
    public IamResponse<PaginationResponse<PostSearchDTO>> findAllPosts(Pageable pageable) {
        Page<PostSearchDTO> posts = postRepository.findAll(pageable)
                .map(postMapper::toPostSearchDTO);
        PaginationResponse<PostSearchDTO> paginationResponse = new PaginationResponse<>(
                posts.getContent(),
                new PaginationResponse.Pagination(
                        posts.getTotalElements(),
                        pageable.getPageSize(),
                        posts.getNumber() + 1,
                        posts.getTotalPages()
                )
        );
        return IamResponse.createSuccessful(paginationResponse);
    }


}
