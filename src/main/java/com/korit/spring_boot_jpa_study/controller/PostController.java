package com.korit.spring_boot_jpa_study.controller;

import com.korit.spring_boot_jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
JPA (Java Persistence API)
객체 지향 언어의 객체와 관계형 데이터베이스의 테이블 간의 매핑을 자동으로 처리(자유도가 낮음)
-> sql을 직접 쓰지 않고 자바 객체 중심으로 DB를 다룸
장점: SQL 없이 빠르게 CRUD가 가능, 코드량 감소
단점: 복잡한 쿼리는 어렵고, 디버깅이 힘들다.
 */

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
}
