package com.sprta_plan_develop.user.repository;

import com.sprta_plan_develop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // 이메일 찾기
    Optional<User> findByUseremail(String useremail);

    // 유저명 찾기
    boolean existsByUsername(String username);
}
