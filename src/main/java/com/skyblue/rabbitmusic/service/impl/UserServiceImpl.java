package com.skyblue.rabbitmusic.service.impl;

import com.skyblue.rabbitmusic.dto.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.exception.BizException;
import com.skyblue.rabbitmusic.enums.StatusCodeEnum;
import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.repository.UserRepository;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    private UserMapper userMapper;

    /**
     * 查询所有用户接口
     * @return List<UserDto>
     */
    @Override
    public List<UserDto> listAll() {
        return userRepository.findAll()
                .stream().map(this.userMapper::toDTO).collect(Collectors.toList());
    }

    /**
     * 创建用户接口
     * @param userCreateRequest 创建用户request
     * @return UserDto
     */
    @Override
    public UserDto create(UserCreateRequest userCreateRequest) {
        checkUsername(userCreateRequest.getUsername());
        User user = userMapper.toEntity(userCreateRequest);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * 校验当前用户是否在数据库存在
     * @param username 用户名
     */
    private void checkUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        user.ifPresent(value -> {
            throw  new BizException(StatusCodeEnum.USER_NAME_DUPLICATE);
        });
    }

    @Override
    public User loadUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new BizException(StatusCodeEnum.USER_NOT_FOUND));
    }
}
