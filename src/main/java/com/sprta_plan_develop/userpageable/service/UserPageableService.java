//package com.sprta_plan_develop.userpageable.service;
//
//
//import com.sprta_plan_develop.comment.repository.CommentRepository;
//import com.sprta_plan_develop.global.exception.CommonException;
//import com.sprta_plan_develop.plan.entity.Plan;
//import com.sprta_plan_develop.plan.repository.PlanRepository;
//import com.sprta_plan_develop.user.repository.UserRepository;
//import com.sprta_plan_develop.userpageable.dto.UserpageableResponse;
//import com.sprta_plan_develop.userpageable.repository.UserPageableRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.awt.print.Pageable;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class UserPageableService {
//
//    private final CommentRepository commentRepository;
//    private final PlanRepository planRepository;
//    private final UserRepository userRepository;
//    private final UserPageableRepository userPageableRepository;
//
//
//
////    @Transactional(readOnly = true)
////    public UserpageableResponse UserPageableAll(Long userId,int page,int size){
////        userRepository.findById(userId).orElseThrow(
////                () -> new CommonException("유저가 없다.")
////        );
////        Pageable pageable= PageRequest.(page,size);
////        Page<Plan> plansPage=userPageableRepository.findByUserId(userId, pageable);
////
////        List<UserpageableResponse> dtos = new ArrayList<>();
////
////        for (Plan plan : ) {
////
////
////            UserpageableResponse dto = new UserpageableResponse(
////
////            );
////             dtos.add(dto);
////
////    }
////        return dtos;
////}
