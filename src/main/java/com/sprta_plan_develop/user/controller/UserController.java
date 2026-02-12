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

        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll(sessionUser));

    }


    // 유저 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(sessionUser,userId));
    }

    // 유저 수정
    @PutMapping("/users/{userId}")
    public ResponseEntity<UpdateUserResponse> updateUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request
    ) {

        return ResponseEntity.status(HttpStatus.OK).body(userService.update(sessionUser,userId, request));
    }

    // 유저 삭제
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUsers(
            @SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser,
            @PathVariable Long userId
    ) {

        userService.delete(sessionUser,userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
