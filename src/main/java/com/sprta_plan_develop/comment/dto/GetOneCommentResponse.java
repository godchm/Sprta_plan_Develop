package com.sprta_plan_develop.comment.dto;

import com.sprta_plan_develop.plan.dto.GetOnePlanResponse;
import lombok.Getter;

@Getter
public class GetOneCommentResponse {
    private final String title;
    private final String content;
    private final String username;

    public GetOneCommentResponse(String username,String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;

    }
}
