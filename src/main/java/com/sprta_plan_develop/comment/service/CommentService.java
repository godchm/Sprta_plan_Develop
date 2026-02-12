package com.sprta_plan_develop.comment.service;

import com.sprta_plan_develop.comment.dto.*;
import com.sprta_plan_develop.comment.entity.Comment;
import com.sprta_plan_develop.comment.repository.CommentRepository;
import com.sprta_plan_develop.global.exception.CommonException;
import com.sprta_plan_develop.plan.dto.CreatePlanRequest;
import com.sprta_plan_develop.plan.dto.CreatePlanResponse;
import com.sprta_plan_develop.plan.dto.GetOnePlanResponse;
import com.sprta_plan_develop.plan.entity.Plan;
import com.sprta_plan_develop.plan.repository.PlanRepository;
import com.sprta_plan_develop.user.entity.User;
import com.sprta_plan_develop.user.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateCommentResponse commentsave(Long userId, Long planId, CreateCommentRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException("유저 정보가 없습니다.")
        );

        Plan plan=planRepository.findById(planId).orElseThrow(
                ()->new CommonException("일정이 없습니다.")
        );

        Comment comment=new Comment(
                request.getContent(),
                user,
                plan
        );
        Comment commentSaved=commentRepository.save(comment);

        return new CreateCommentResponse(
                commentSaved.getContent()
        );

    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOneCommentResponse commentOne(Long userId, Long planId, Long commentId) {

        Comment comment=commentRepository.findByIdAndUserIdAndPlanId(userId,planId,commentId).orElseThrow(
                ()-> new CommonException("없는 일정이거나, 유저정보가 없습니다.")
        );

        return new GetOneCommentResponse (
                comment.getUser().getUsername(),
                comment.getPlan().getTitle(),
                comment.getContent()

        );
    }

     // 모두 조회
    @Transactional(readOnly = true)
    public List<GetCommentsResponse> commentAll(Long userId, Long planId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException("유저정보가 없습니다."));

        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new CommonException("일정이 없습니다."));

        List<Comment> comments = commentRepository.findByPlanAndUser(plan, user);
        List<GetCommentsResponse> dtos = new ArrayList<>();

        for (Comment comment : comments) {
            GetCommentsResponse dto = new GetCommentsResponse(
                    comment.getId(),
                    comment.getUser().getUsername(),
                    comment.getPlan().getTitle(),
                    comment.getPlan().getContent(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );

            dtos.add(dto);
        }
        return dtos;
    }

    // 댓글 수정
    @Transactional
    public UpdateCommentResponse commentUpdate(Long userId, Long planId, Long commentId,UpdateCommentRequest request) {
        Comment comment = commentRepository.findByIdAndUserIdAndPlanId(userId, planId, commentId).orElseThrow(
                () -> new CommonException("없는 일정이거나, 유저정보가 없습니다.")
        );
        comment.CommentUpdate(
                request.getContent()
        );

        return new UpdateCommentResponse(
                comment.getId(),
                comment.getContent()
        );
    }
        // 댓글 삭제
        @Transactional
        public void commentDelete(Long commentId) {
        boolean existence=commentRepository.existsById(commentId);
        if (!existence){
            throw new CommonException("유저, 일정, 댓글 정보가 없습니다.");
        }
        commentRepository.deleteById(commentId);

        }





}
