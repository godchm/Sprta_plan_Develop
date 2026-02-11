package com.sprta_plan_develop.plan.service;


import com.sprta_plan_develop.plan.dto.*;
import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.plan.repository.PlanRepository;
import com.sprta_plan_develop.user.entity.User;
import com.sprta_plan_develop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;
    private final UserRepository userRepository;


    // 새 일정 입력
    @Transactional
    public CreatePlanResponse save(Long userId,CreatePlanRequest request) {
      User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 게시글입니다.")
        );

      Plan plan=new Plan(
              request.getTitle(),
              request.getContent(),
              user
              );
      Plan planSaved=planRepository.save(plan);

      return new CreatePlanResponse(
              planSaved.getTitle(),
              planSaved.getContent(),
              planSaved.getCreatedAt(),
              planSaved.getModifiedAt()
      );
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOnePlanResponse getOne(Long userId,Long planId){
        // 일정 한건 조회
        Plan plan=planRepository.findByIdAndUserId(planId,userId).orElseThrow(
                ()-> new IllegalStateException("없는 유저 입니다.")
        );
        return new GetOnePlanResponse(
                plan.getId(),
                plan.getTitle(),
                plan.getContent(),
                plan.getUser().getUsername(),
                plan.getCreatedAt(),
                plan.getModifiedAt()
        );

    }



    // 모두 조회
    @Transactional(readOnly = true)
    public List<GetOnePlanResponse> getAll(Long userId) {

       User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        List<Plan> plans = planRepository.findByUser(user);

        List<GetOnePlanResponse> dtos = new ArrayList<>();

        for (Plan plan : plans) {
            GetOnePlanResponse dto = new GetOnePlanResponse(
                    plan.getId(),
                    plan.getTitle(),
                    plan.getContent(),
                    plan.getUser().getUsername(),
                    plan.getCreatedAt(),
                    plan.getModifiedAt()
            );
            dtos.add(dto);
        }

        return dtos;


}

    // 일정 수정하기
    @Transactional
    public UpdatePlanResponse update(Long plantId, UpdatePlanRequest request) {
         Plan plan  = planRepository.findById(plantId).orElseThrow(
                () -> new IllegalStateException("없는 댓글입니다.")
        );
        plan.planUpdate(
                request.getTitle(),
                request.getContent()
                );
        return new UpdatePlanResponse(
                plan.getTitle(),
                plan.getContent(),
                plan.getCreatedAt(),
                plan.getModifiedAt()
        );
    }

    // 삭제
    @Transactional
    public void delete(Long planId) {
        boolean existence = planRepository.existsById(planId);
        if (!existence) {
            throw new IllegalStateException("없는 댓글입니다.");
        }
        planRepository.deleteById(planId);
    }
}