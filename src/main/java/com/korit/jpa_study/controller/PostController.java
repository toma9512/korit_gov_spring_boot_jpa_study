package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.repository.PostRepository;
import com.korit.jpa_study.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private PostRepository postRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody AddPostReqDto addPostReqDto) {
        return ResponseEntity.ok(postService.addPost(addPostReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPostAll() {
        return ResponseEntity.ok(postService.getPostAll());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostByPostId(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.getPostByPostId(postId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editPost(@RequestBody EditPostReqDto editPostReqDto) {
        return ResponseEntity.ok(postService.editPost(editPostReqDto));
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
        return ResponseEntity.ok(postService.removePost(postId));
    }

    @GetMapping("/get/user/{userId}")
    public ResponseEntity<?> getPostByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }
}
