package com.sprta_plan_develop.comment.controller;


import com.sprta_plan_develop.comment.dto.CreateCommentRequest;
import com.sprta_plan_develop.comment.dto.CreateCommentResponse;
import com.sprta_plan_develop.comment.dto.GetOneCommentResponse;
import com.sprta_plan_develop.comment.service.CommentService;
import com.sprta_plan_develop.plan.dto.GetOnePlanResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    // 댓글 생성
    @PostMapping("/users/{userId}/plans/{planId}/comments")
    public ResponseEntity<CreateCommentResponse> createComment(
            @PathVariable Long userId,
            @PathVariable Long planId,
            @Valid @RequestBody CreateCommentRequest request
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.commentsave(userId,planId,request));
    }

    // 댓글 조회
    @GetMapping("/users/{userId}/plans/{planId}/comments/{commentId}")
    public ResponseEntity<GetOneCommentResponse> createOneComment(
            @PathVariable Long userId,
            @PathVariable Long planId,
            @PathVariable Long commentId

    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.commentOne(userId,planId,commentId));
    }

    // 댓글 모두조회
    @GetMapping("/users/{userId}/plans/{planId}/comments/{commentId}")
    public ResponseEntity<List<GetOneCommentResponse>> createAllComment(
            @PathVariable Long userId,
            @PathVariable Long planId


    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.commentAll(userId,planId));
    }
}
