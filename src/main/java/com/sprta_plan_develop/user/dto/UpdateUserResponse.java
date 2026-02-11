package com.sprta_plan_develop.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserResponse {

    private final Long id;
    private final String uername;
    private final String useremail;


    public UpdateUserResponse(Long id, String uername, String useremail) {
        this.id = id;
        this.uername = uername;
        this.useremail = useremail;
    }
}
