package com.sprta_plan_develop.plan.repository;

import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    // 수정일 차순 정렬을 위해
    List<Plan> findAllByOrderByModifiedAtDesc();

    // 특정 유저가 가진 모든 일정 조회
    List<Plan>findByUser(User user);

    // 특정 유저의 특정 일정 1개 조회
    Optional<Plan> findByIdAndUserId(Long planId, Long userId);
}
