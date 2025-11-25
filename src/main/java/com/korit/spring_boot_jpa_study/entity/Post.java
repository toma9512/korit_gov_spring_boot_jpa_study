package com.korit.spring_boot_jpa_study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Integer todoId;
    private String title;
    private String content;
    private Integer userId;
    private LocalDateTime createDt;
    private LocalDateTime updateDt;
}
