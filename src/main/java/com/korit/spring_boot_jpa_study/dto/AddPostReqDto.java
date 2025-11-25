package com.korit.spring_boot_jpa_study.dto;

import com.korit.spring_boot_jpa_study.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostReqDto {
    private String title;
    private String content;
    private Integer userId;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }
}
