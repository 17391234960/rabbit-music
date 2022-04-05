package com.skyblue.rabbitmusic.controller;

import com.skyblue.rabbitmusic.dto.request.TokenCreateRequest;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.vo.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@AllArgsConstructor
public class TokenController {

    private final UserService userService;

    /**
     * 创建token
     * @param tokenCreateRequest 创建请求参数
     * @return token
     */
    @PostMapping
    public Result<String> create(@RequestBody TokenCreateRequest tokenCreateRequest) {
        return Result.ok(userService.createToken(tokenCreateRequest));
    }


}
