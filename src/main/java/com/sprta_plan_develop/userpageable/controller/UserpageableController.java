//package com.sprta_plan_develop.userpageable.controller;
//
//
//import com.sprta_plan_develop.userpageable.dto.UserpageableResponse;
//import com.sprta_plan_develop.userpageable.service.UserPageableService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//public class UserpageableController {
//
//    private final UserPageableService userPageableService;
//
//    @GetMapping("/users/all/{userId}")
//    public ResponseEntity<List<UserpageableResponse>> UserAll(
//            @PathVariable Long userId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//            )
//    {
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(userPageableService.UserPageableAll(userId,page,size));
//    }
//}
