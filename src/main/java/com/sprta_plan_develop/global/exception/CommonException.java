package com.sprta_plan_develop.global.exception;

import org.springframework.http.HttpStatus;

// extends ServerException 중요!
public class CommonException extends ServerException {
    public CommonException(String message) {
        super(HttpStatus.NOT_FOUND, message); // HttpStatus.NOT_FOUND 지정
    }
}
