package com.skyblue.rabbitmusic.exception;

import lombok.Data;

/**
 * 错误响应对象
 */
@Data
public class ErrorResponse {
    private Integer code;

    private String message;

    private Object trace;
}
