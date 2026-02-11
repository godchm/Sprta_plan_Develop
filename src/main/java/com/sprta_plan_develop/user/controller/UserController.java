package com.sprta_plan_develop.user.controller;

import com.sprta_plan_develop.user.dto.*;
import com.sprta_plan_develop.user.entity.User;
import com.sprta_plan_develop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입. 유저 생성
    @PostMapping("/users/register")
    public ResponseEntity<CreateUserResponse> register(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.register(request);
        CreateUserResponse response = new CreateUserResponse(
                user.getId(),
                user.getUsername()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


// 로그인
    @PostMapping("/users/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        SessionUser sessionUser = userService.login(request);

        session.setAttribute("loginUser", sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    // 로그아웃
    @PostMapping("/users/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, HttpSession session) {
        if (sessionUser == null) {
            return ResponseEntity.badRequest().build();
        }

        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 유저 모두  조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAll(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser
    ) {
        UserLogin(sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());

    }


    // 유저 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId
    ) {
        UserLoginId(sessionUser, userId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(userId));
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request
    ) {
        UserLoginId(sessionUser, userId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId
    ) {
        UserLoginId(sessionUser, userId);
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 로그인 조건문
    private SessionUser UserLogin(SessionUser user) {
        if (user == null) {
            throw new IllegalArgumentException("로그인이 필요.");
        }
        return user;
    }

    // 권한 기능.
    private void UserLoginId(SessionUser user, Long userId) {
        UserLogin(user);
        if (!user.getId().equals(userId)) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }


}
