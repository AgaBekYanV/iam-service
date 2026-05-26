package com.my.iam_service.service;

import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.response.IamResponse;
import jakarta.validation.constraints.NotNull;


public interface PostService {

    IamResponse<PostDTO> getPostById(@NotNull Integer id);
}
