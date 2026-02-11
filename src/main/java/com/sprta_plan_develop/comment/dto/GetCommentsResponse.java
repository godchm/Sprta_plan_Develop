package com.sprta_plan_develop.comment.dto;


import lombok.Getter;

@Getter
public class GetCommentsResponse {
    private final String content;

    public GetCommentsResponse(String content) {
        this.content = content;
    }
}
