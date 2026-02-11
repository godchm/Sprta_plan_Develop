package com.sprta_plan_develop.user.dto;

import lombok.Getter;

@Getter
public class GetUserResponse {

    private final Long id;
    private final String username;
    private final String useremail;


    public GetUserResponse(Long id, String username, String useremail) {
        this.id = id;
        this.username = username;
        this.useremail = useremail;
    }
}
