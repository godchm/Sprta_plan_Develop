package com.sprta_plan_develop.plan.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePlanRequest {

    @NotBlank(message = "제목은 필수입니다.")
    private String title;


    @Size(min = 30, message = "내용은 최소 30자 이상이어야 합니다.")
    private String content;



}
