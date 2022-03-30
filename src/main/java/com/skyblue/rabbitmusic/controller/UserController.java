package com.skyblue.rabbitmusic.controller;

import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.vo.UserVo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    private UserMapper userMapper;


    @RequestMapping("/")
    public List<UserVo> listAll() {
        return userService.listAll()
                .stream().map(this.userMapper::toVo).collect(Collectors.toList());
    }

}
