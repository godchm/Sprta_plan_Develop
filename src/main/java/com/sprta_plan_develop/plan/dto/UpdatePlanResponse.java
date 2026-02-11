package com.sprta_plan_develop.plan.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePlanResponse {
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public UpdatePlanResponse(String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.title = title;
        this.content=content;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
