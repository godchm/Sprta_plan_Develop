package com.sprta_plan_develop.plan.repository;

import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan,Long> {
    List<Plan> findAllByOrderByModifiedAtDesc();
    List<Plan>findByUser(User user);
    Optional<Plan> findByIdAndUserId(Long planId, Long userId);
}
