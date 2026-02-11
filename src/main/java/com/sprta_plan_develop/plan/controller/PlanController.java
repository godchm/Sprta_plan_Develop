package com.sprta_plan_develop.plan.controller;


import com.sprta_plan_develop.plan.dto.*;
import com.sprta_plan_develop.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;


    @PostMapping("/users/{userId}/plans")
    public ResponseEntity<CreatePlanResponse> createPlan(
            @PathVariable Long userId,
            @RequestBody CreatePlanRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(planService.save(userId,request));
    }

    // 단건조회
    @GetMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<GetOnePlanResponse> getOnePlan(
            @PathVariable Long userId,
            @PathVariable Long planId
    ){
        return ResponseEntity.status(HttpStatus.OK).body(planService.getOne(userId,planId));
    }

    // 모두 조회
    @GetMapping("/users/{userId}/plans")
    public ResponseEntity<List<GetOnePlanResponse>> getAllPlan(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(planService.getAll(userId));
    }



    // 수정
    @PutMapping("/users/{userId}/plans/{planId}")
    public ResponseEntity<UpdatePlanResponse> updateComment(
            @PathVariable Long planId,
            @RequestBody UpdatePlanRequest request
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(planService.update(planId, request));
    }

    // 삭제 기능
    @DeleteMapping("users/{userId}/plans/{planId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long planId
    ) {
        planService.delete(planId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
