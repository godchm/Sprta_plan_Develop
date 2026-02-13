package com.sprta_plan_develop.comment.repository;

import com.sprta_plan_develop.comment.entity.Comment;
import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPlanAndUser(Plan plan, User user);
    Optional<Comment> findByIdAndUserIdAndPlanId(Long userId, Long planId, Long commentId);
//    long countByPlanId(Long planId);


    // 댓글 정보 가져오기. 삭제 기능 구현 위해.
    void deleteByPlanUserId(Long userId);
}
