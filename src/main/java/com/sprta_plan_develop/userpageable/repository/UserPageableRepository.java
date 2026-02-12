package com.sprta_plan_develop.userpageable.repository;

import com.sprta_plan_develop.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPageableRepository extends JpaRepository<Long, Plan> {
}
