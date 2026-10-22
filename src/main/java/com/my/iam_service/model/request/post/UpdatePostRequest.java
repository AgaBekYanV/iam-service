package com.my.iam_service.model.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePostRequest implements Serializable {

    @NotBlank(message = "Title cannot be empty")
    String title;

    @NotBlank(message = "Content cannot be empty")
    String content;

    @NotNull(message = "Specify the number of likes")
    Integer likes;

}
