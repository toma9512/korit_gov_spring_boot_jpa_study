package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.AddPostReqDto;
import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.EditPostReqDto;
import com.korit.jpa_study.entity.Post;
import com.korit.jpa_study.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ApiRespDto<?> addPost(AddPostReqDto addPostReqDto) {
        Optional<Post> foundPost = postRepository.findByTitle(addPostReqDto.getTitle());

        if (foundPost.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 title", addPostReqDto.getTitle());
        }

        return new ApiRespDto<>("success", "게시글 추가 성공", postRepository.save(addPostReqDto.toEntity()));
    }

    public ApiRespDto<?> getPostAll() {
        return new ApiRespDto<>("success", "전체 조회", postRepository.findAll());
    }

    public ApiRespDto<?> getPostByPostId(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);

        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다.", null);
        }

        return new ApiRespDto<>("success", "단건 조회", foundPost.get());
    }

    public ApiRespDto<?> editPost(EditPostReqDto editPostReqDto) {
        Optional<Post> foundPost = postRepository.findById(editPostReqDto.getPostId());

        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 게시글", null);
        }

        Post post = foundPost.get();
        post.setTitle(editPostReqDto.getTitle());
        post.setContent(editPostReqDto.getContent());
        post.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "수정 성공", postRepository.save(post));
    }

    public ApiRespDto<?> removePost(Integer postId) {
        Optional<Post> foundPost = postRepository.findById(postId);

        if (foundPost.isEmpty()) {
            return new ApiRespDto<>("failed", "존재하지 않는 게시글", null);
        }

        postRepository.deleteById(postId);
        return new ApiRespDto<>("success", "삭제 성공", null);
    }

    public ApiRespDto<?> getPostByUserId(Integer userId) {
        List<Post> postList = postRepository.findByUserId(userId);

        if (postList.isEmpty()) {
            return new ApiRespDto<>("failed", "조회 내용 없음", null);
        }

        return new ApiRespDto<>("success", "userId 조회", postList);
    }
}
