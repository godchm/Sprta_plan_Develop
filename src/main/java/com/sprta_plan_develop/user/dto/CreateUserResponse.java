package com.sprta_plan_develop.user.dto;

import lombok.Getter;

@Getter
public class CreateUserResponse {

    private final Long id;
    private final String username;
//    private final String usereamil;

    public CreateUserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
//        this.usereamil = usereamil;
    }
}
