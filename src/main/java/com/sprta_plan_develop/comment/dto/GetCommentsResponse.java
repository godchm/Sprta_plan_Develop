package com.sprta_plan_develop.comment.dto;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentsResponse {

    // content가 일정이랑, 댓글에 있어서 두개를 나눠서 설정했다.

    private final Long id;
    private final String username;
    private final String plantitle;
    private final String plancontent;
    private final String commentcontent;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;


    public GetCommentsResponse(Long id, String username, String plantitle, String plancontent, String commentcontent, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.username = username;
        this.plantitle = plantitle;
        this.plancontent = plancontent;
        this.commentcontent = commentcontent;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
