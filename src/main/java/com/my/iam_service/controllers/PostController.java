package com.my.iam_service.controllers;

import com.my.iam_service.model.constants.ApiLogMessage;
import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.response.IamResponse;
import com.my.iam_service.service.PostService;
import com.my.iam_service.utils.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
