package com.sprta_plan_develop.comment.dto;


import lombok.Getter;

@Getter
public class CreateCommentResponse {
    private final String content;

    public CreateCommentResponse(String content) {
        this.content = content;
    }
}
