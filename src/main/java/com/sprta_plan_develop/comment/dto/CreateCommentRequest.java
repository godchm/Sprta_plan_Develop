package com.sprta_plan_develop.comment.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {


    @Size(min = 15, message = "내용은 최소 15자 이상이어야 합니다.")
    private String content;


}
