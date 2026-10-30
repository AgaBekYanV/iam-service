package com.my.iam_service.model.dto.post;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostSearchDTO implements Serializable {
    Integer id;
    String title;
    String content;
    Integer likes;
    LocalDateTime created;
    Boolean isDeleted;
}
