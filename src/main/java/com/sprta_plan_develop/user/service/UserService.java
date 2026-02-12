package com.sprta_plan_develop.user.service;

import com.sprta_plan_develop.global.config.PasswordEncoder;
import com.sprta_plan_develop.global.exception.CommonException;
import com.sprta_plan_develop.user.dto.*;
import com.sprta_plan_develop.user.entity.User;
import com.sprta_plan_develop.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입
    @Transactional
    public User register(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CommonException("이미 존재하는 사용자입니다.");
        }

        User user = new User(
                request.getUsername(),
                request.getUseremail(),
                passwordEncoder.encode(request.getPassword())

        );

        return userRepository.save(user);
    }


    // 모두 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll( SessionUser sessionUser) {
        UserLogin(sessionUser);
        List<User> users = userRepository.findAll();

        List<GetUserResponse> dtos = new ArrayList<>();
        for (User user : users) {
            GetUserResponse dto = new GetUserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getUseremail()
            );
            dtos.add(dto);
        }

        return dtos;
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getOne(SessionUser sessionUser,Long userId) {
        UserLoginId(sessionUser, userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException("없는 게시글 입니다.")
        );

        return new GetUserResponse(
                user.getId(),
                user.getUsername(),
                user.getUseremail()
        );
    }

    // 수정
    @Transactional
    public UpdateUserResponse update(SessionUser sessionUser,Long userId, UpdateUserRequest request) {

        UserLoginId(sessionUser, userId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException("없는 게시글 입니다.")
        );
        user.update(request.getUsername(), request.getUseremail());
        return new UpdateUserResponse(
                user.getId(),
                user.getUsername(),
                user.getUseremail()
        );
    }

    // 삭제
    @Transactional
    public void delete(SessionUser sessionUser,Long userId) {
        UserLoginId(sessionUser, userId);
        boolean existence = userRepository.existsById(userId);
        if (!existence) {
            throw new CommonException("없는 게시글 입니다.");
        }
        userRepository.deleteById(userId);
    }

    // 로그인
    @Transactional(readOnly = true)
    public SessionUser login(@Valid LoginRequest request) {
        User user=userRepository.findByUseremail(request.getUseremail())
                .orElseThrow( ()->new IllegalArgumentException("이메일이 존재하지 않습니다."));
        if (!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new CommonException("비밀번호가 틀렸습니다.");
        }
        return new SessionUser(user.getId());
    }


    // 로그인 조건문
    private SessionUser UserLogin(SessionUser user) {
        if (user == null) {
            throw new CommonException("로그인이 필요.");
        }
        return user;
    }

    // 권한 기능.
    private void UserLoginId(SessionUser user, Long userId) {
        UserLogin(user);
        if (!user.getId().equals(userId)) {
            throw new CommonException("권한이 없습니다.");
        }
    }
}