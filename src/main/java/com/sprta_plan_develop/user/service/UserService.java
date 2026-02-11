package com.sprta_plan_develop.user.service;

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


    // 회원가입
    @Transactional
    public User register(CreateUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CommonException("이미 존재하는 사용자입니다.");
        }

        User user = new User(
                request.getUsername(),
                request.getUseremail(),
                request.getPassword()

        );

        return userRepository.save(user);
    }


    // 모두 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
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
    public GetUserResponse getOne(Long postingId) {
       User user = userRepository.findById(postingId).orElseThrow(
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
    public UpdateUserResponse update(Long postingId, UpdateUserRequest request) {
        User user = userRepository.findById(postingId).orElseThrow(
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
    public void delete(Long postingId) {
        boolean existence = userRepository.existsById(postingId);
        if (!existence) {
            throw new CommonException("없는 게시글 입니다.");
        }
        userRepository.deleteById(postingId);
    }

    // 로그인
    @Transactional(readOnly = true)
    public SessionUser login(@Valid LoginRequest request) {
        User user=userRepository.findByUseremail(request.getUseremail())
                .orElseThrow( ()->new IllegalArgumentException("이메일이 존재하지 않습니다."));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new CommonException("비밀번호가 틀렸습니다.");
        }
        return new SessionUser(user.getId());
    }
}