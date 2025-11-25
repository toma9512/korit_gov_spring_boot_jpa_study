package com.korit.jpa_study.controller;

import com.korit.jpa_study.dto.DeleteUserReqDto;
import com.korit.jpa_study.dto.EditUserReqDto;
import com.korit.jpa_study.dto.SignupReqDto;
import com.korit.jpa_study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupReqDto signupReqDto) {
        return ResponseEntity.ok(userService.signup(signupReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getUserAll() {
        return ResponseEntity.ok(userService.getUserAll());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody EditUserReqDto editUserReqDto) {
        return ResponseEntity.ok(userService.editUser(editUserReqDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody DeleteUserReqDto deleteUserReqDto) {
        return ResponseEntity.ok(userService.deleteUser(deleteUserReqDto));
    }
}
