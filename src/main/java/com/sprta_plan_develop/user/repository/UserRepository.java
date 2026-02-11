package com.sprta_plan_develop.user.repository;

import com.sprta_plan_develop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUseremail(String useremail);

    boolean existsByUsername(String username);
}
