package com.skyblue.rabbitmusic.controller;

import com.skyblue.rabbitmusic.dto.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.vo.Result;
import com.skyblue.rabbitmusic.vo.UserVo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private UserMapper userMapper;


    @GetMapping("/")
    public Result<List<UserVo>> listAll() {
        return Result.ok(userService.listAll()
                .stream().map(this.userMapper::toVo).collect(Collectors.toList()));
    }


    @PostMapping("/")
    public Result<UserVo> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = userService.create(userCreateRequest);
        return Result.ok(userMapper.toVo(userDto));
    }

}
