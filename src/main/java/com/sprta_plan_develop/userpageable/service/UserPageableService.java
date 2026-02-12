package com.sprta_plan_develop.userpageable.service;


import com.sprta_plan_develop.comment.repository.CommentRepository;
import com.sprta_plan_develop.plan.repository.PlanRepository;
import com.sprta_plan_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPageableService {

    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
}
