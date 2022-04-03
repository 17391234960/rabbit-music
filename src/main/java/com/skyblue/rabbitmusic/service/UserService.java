package com.skyblue.rabbitmusic.service;

import com.skyblue.rabbitmusic.dto.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    /**
     * 查询所有用户接口
     * @return List<UserDto>
     */
    List<UserDto> listAll();

    /**
     * 创建用户接口
     * @param userCreateRequest 创建用户request
     * @return UserDto
     */
    UserDto create(UserCreateRequest userCreateRequest);

    @Override
    UserDetails loadUserByUsername(String username);
}
