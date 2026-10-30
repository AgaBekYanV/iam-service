package com.my.iam_service.mapper;

import com.my.iam_service.model.dto.post.PostDTO;
import com.my.iam_service.model.dto.post.PostSearchDTO;
import com.my.iam_service.model.entity.Post;
import com.my.iam_service.model.request.post.NewPostRequest;
import com.my.iam_service.model.request.post.UpdatePostRequest;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        imports = {DateTimeUtils.class, Object.class}
)
public interface PostMapper {

    PostDTO toPostDTO(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    Post createPost(NewPostRequest newPostRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created", ignore = true)
    void updatePost(@MappingTarget Post post, UpdatePostRequest updatePostRequest);

    @Mapping(source = "deleted",  target = "isDeleted")
    PostSearchDTO toPostSearchDTO(Post post);
}
