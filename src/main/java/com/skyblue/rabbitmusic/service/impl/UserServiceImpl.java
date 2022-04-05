package com.skyblue.rabbitmusic.service.impl;

import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.dto.request.TokenCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserUpdateRequest;
import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.enums.StatusCodeEnum;
import com.skyblue.rabbitmusic.exception.BizException;
import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.repository.UserRepository;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.utils.JWTUtils;
import com.skyblue.rabbitmusic.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {
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
        User user = userMapper.createEntity(userCreateRequest);
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

    /**
     * 创建Token
     *
     * @param tokenCreateRequest 请求数据
     * @return token
     */
    @Override
    public String createToken(TokenCreateRequest tokenCreateRequest) {
        User user = loadUserByUsername(tokenCreateRequest.getUsername());
        if (!SecurityUtils.matchesPassword(tokenCreateRequest.getPassword(), user.getPassword())) {
            throw new BizException(StatusCodeEnum.USER_PASSWORD_NOT_MATCH);
        }
        if (!user.isEnabled()) {
            throw new BizException(StatusCodeEnum.USER_NOT_ENABLED);
        }
        if (!user.isAccountNonLocked()) {
            throw new BizException(StatusCodeEnum.USER_LOCKED);
        }
        return JWTUtils.generateToken(user.getUsername());
    }

    /**
     * 获取当前用户
     *
     * @return 当前用户信息
     */
    @Override
    public UserDto getCurrentUser() {
        return userMapper.toDTO(super.getCurrentUserEntity());
    }

    /**
     * 根据id 查询当前用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public UserDto get(String id) {
        return userMapper.toDTO(getById(id));
    }

    /**
     * 修改用户信息
     *
     * @param id                用户id
     * @param userUpdateRequest 用户信息参数
     * @return 修改成功后的用户信息
     */
    @Override
    public UserDto update(String id, UserUpdateRequest userUpdateRequest) {
        return userMapper.toDTO(userRepository.save(userMapper.updateEntity(getById(id), userUpdateRequest)));
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @Override
    public void delete(String id) {
        userRepository.delete(getById(id));
    }

    /**
     * 搜索用户
     *
     * @param pageable 分页参数
     * @return 搜索数据
     */
    @Override
    public Page<UserDto> search(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        if (pageNumber>0) {
            pageNumber = pageable.getPageNumber() - 1;
        }
        return userRepository.findAll(PageRequest.of(pageNumber, pageable.getPageSize())).map(userMapper::toDTO);
    }

    private User getById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new BizException(StatusCodeEnum.USER_NOT_FOUND);
        }
        return user.get();
    }

}
