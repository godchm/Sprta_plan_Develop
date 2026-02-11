package com.sprta_plan_develop.plan.dto;

import com.sprta_plan_develop.user.entity.User;

import java.time.LocalDateTime;

public class  CreatePlanResponse {

    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CreatePlanResponse(String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
