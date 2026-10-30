package com.my.iam_service.controllers;

import com.my.iam_service.model.constants.ApiLogMessage;
import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.dto.post.PostSearchDTO;
import com.my.iam_service.model.request.post.NewPostRequest;
import com.my.iam_service.model.request.post.UpdatePostRequest;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.model.response.PaginationResponse;
import com.my.iam_service.service.PostService;
import com.my.iam_service.utils.ApiUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${end.point.posts}")
public class PostController {

    private final PostService postService;

    @GetMapping("${end.point.id}")
    public ResponseEntity<IamResponse<PostDTO>> getPostById(
            @PathVariable(name = "id") Integer postId
    ) {
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodeName());
        IamResponse<PostDTO> response = postService.getPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("${end.point.create}")
    public ResponseEntity<IamResponse<PostDTO>> createPost(
            @RequestBody @Valid NewPostRequest newPostRequest){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodeName());
        IamResponse<PostDTO> response = postService.createPost(newPostRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("${end.point.id}")
    public ResponseEntity<IamResponse<PostDTO>> updatePostById(
            @PathVariable(name = "id") Integer postId,
            @RequestBody @Valid UpdatePostRequest request){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodeName());

        IamResponse<PostDTO> updatedPost = postService.updatePost(postId, request);
        return ResponseEntity.ok(updatedPost);

    }

    @DeleteMapping("${end.point.id}")
    public ResponseEntity<Void> softDeletePostById(
            @PathVariable(name = "id") Integer postId
    ){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodeName());
        postService.softDeletePost(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("${end.point.all}")
    public ResponseEntity<IamResponse<PaginationResponse<PostSearchDTO>>> getAllPosts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            Pageable pageable){
        log.trace(ApiLogMessage.NAME_OF_CURRENT_METHOD.getValue(), ApiUtils.getMethodeName());

        Pageable pegeable = PageRequest.of(page, limit);
        IamResponse<PaginationResponse<PostSearchDTO>> response = postService.findAllPosts(pageable);
        return ResponseEntity.ok(response);
    }
}
