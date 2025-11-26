package com.korit.jpa_study.service;

import com.korit.jpa_study.dto.ApiRespDto;
import com.korit.jpa_study.dto.DeleteUserReqDto;
import com.korit.jpa_study.dto.EditUserReqDto;
import com.korit.jpa_study.dto.SignupReqDto;
import com.korit.jpa_study.entity.User;
import com.korit.jpa_study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ApiRespDto<?> signup(SignupReqDto signupReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(signupReqDto.getUsername());

        if (foundUser.isPresent()) {
            return new ApiRespDto<>("failed", "중복된 username", signupReqDto.getUsername());
        }

        return new ApiRespDto<>("success", "회원 가입 성공", userRepository.save(signupReqDto.toEntity()));
    }

    public ApiRespDto<?> getUserAll() {
        return new ApiRespDto<>("success", "전체 조회", userRepository.findAll());
    }

    public ApiRespDto<?> getUserByUsername(String username) {
        Optional<User> foundUser = userRepository.findUserByUsername(username);

        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "조회된 회원 없음", username);
        }

        return new ApiRespDto<>("success", "username 조회", foundUser.get());
    }

    public ApiRespDto<?> getUserByUserId(Integer userId) {
        Optional<User> foundUser = userRepository.findUserByUserId(userId);

        if (foundUser.isEmpty()) {
            return new ApiRespDto<>("failed", "조회된 회원 없음", userId);
        }

        return new ApiRespDto<>("success", "userId 조회", foundUser.get());
    }

    public ApiRespDto<?> editUser(EditUserReqDto editUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(editUserReqDto.getUsername());

        if (foundUser.isEmpty() || !foundUser.get().getPassword().equals(editUserReqDto.getPassword())) {
            return new ApiRespDto<>("failed", "회원 정보가 일치하지 않음", editUserReqDto);
        }

        User user = foundUser.get();
        user.setPassword(editUserReqDto.getNewPassword());
        user.setEmail(editUserReqDto.getEmail());
        user.setUpdateDt(LocalDateTime.now());
        return new ApiRespDto<>("success", "회원 정보 수정 완료", userRepository.save(user));
    }

    public ApiRespDto<?> deleteUser(DeleteUserReqDto deleteUserReqDto) {
        Optional<User> foundUser = userRepository.findUserByUsername(deleteUserReqDto.getUsername());

        if (foundUser.isEmpty() || !foundUser.get().getPassword().equals(deleteUserReqDto.getPassword())) {
            return new ApiRespDto<>("failed", "회원 정보가 일치하지 않음", deleteUserReqDto);
        }

        userRepository.delete(foundUser.get());
        return new ApiRespDto<>("success", "회원 삭제 완료", foundUser.get());
    }
}
