package com.skyblue.rabbitmusic.service;

import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.dto.request.TokenCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserUpdateRequest;
import com.skyblue.rabbitmusic.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    User loadUserByUsername(String username);

    /**
     * 创建Token
     * @param tokenCreateRequest 请求数据
     * @return token
     */
    String createToken(TokenCreateRequest tokenCreateRequest);

    /**
     * 获取当前用户
     * @return 当前用户信息
     */
    UserDto getCurrentUser();

    /**
     * 根据id 查询当前用户
     * @param id 用户id
     * @return 用户信息
     */
    UserDto get(String id);

    /**
     * 修改用户信息
     * @param id 用户id
     * @param userUpdateRequest 用户信息参数
     * @return 修改成功后的用户信息
     */
    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    /**
     * 删除用户
     * @param id 用户id
     */
    void delete(String id);


    /**
     * 搜索用户
     * @param pageable 分页参数
     * @return 搜索数据
     */
    Page<UserDto> search(Pageable pageable);
}
