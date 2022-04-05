package com.skyblue.rabbitmusic.handler;

import com.skyblue.rabbitmusic.enums.StatusCodeEnum;
import com.skyblue.rabbitmusic.exception.BizException;
import com.skyblue.rabbitmusic.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义错误
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse bizExceptionHandler(BizException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(e.getCode());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTrace(e.getStackTrace());
        log.error(e.getMessage());
        return errorResponse;
    }

    /**
     * 系统错误
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse exceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(StatusCodeEnum.INNER_ERROR.getCode());
        errorResponse.setMessage(StatusCodeEnum.INNER_ERROR.getMessage());
//        e.printStackTrace();
        log.error(e.getMessage());
        return errorResponse;
    }

    /**
     * 权限不足异常
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse accessDeniedHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(StatusCodeEnum.FORBIDDEN.getCode());
        errorResponse.setMessage(StatusCodeEnum.FORBIDDEN.getMessage());
//        e.printStackTrace();
        log.error("不允许访问,权限级别不够");
//        log.error(e.getMessage());
        return errorResponse;
    }

    /**
     * 方法不允许错误
     * @param e 异常
     * @return 异常信息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> bizExceptionHandler(MethodArgumentNotValidException e) {

        List<ErrorResponse> errorResponses = new ArrayList<>();
        e.printStackTrace();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setCode(StatusCodeEnum.BAD_REQUEST.getCode());
            errorResponse.setMessage(error.getDefaultMessage());
            errorResponses.add(errorResponse);
            log.error(error.getDefaultMessage());
        });

        return errorResponses;
    }

}
