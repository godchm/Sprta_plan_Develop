package com.sprta_plan_develop.userpageable.controller;


import com.sprta_plan_develop.userpageable.dto.UserpageableResponse;
import com.sprta_plan_develop.userpageable.service.UserPageableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserpageableController {

    private final UserPageableService userPageableService;

    @GetMapping("/users/all")
    public ResponseEntity<List<UserpageableResponse>> UserAll(){

        return
    }
}
