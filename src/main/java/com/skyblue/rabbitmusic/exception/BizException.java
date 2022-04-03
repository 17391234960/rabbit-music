package com.skyblue.rabbitmusic.exception;


import com.skyblue.rabbitmusic.enums.StatusCodeEnum;

public class BizException extends RuntimeException {

    private final Integer code;

    public BizException(StatusCodeEnum exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
