package com.skyblue.rabbitmusic.controller;

import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.dto.request.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserUpdateRequest;
import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.service.UserService;
import com.skyblue.rabbitmusic.vo.Result;
import com.skyblue.rabbitmusic.vo.UserVo;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final UserMapper userMapper;


    /*
     * 查询所有用户列表
     * @return 用户信息 list
     */
//    @GetMapping("/")
//    public Result<List<UserVo>> listAll() {
//        return Result.ok(userService.listAll()
//                .stream().map(this.userMapper::toVo).collect(Collectors.toList()));
//    }

    /**
     * 用户检索
     * @param pageable 分页参数
     * @return 检索数据
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Result<Page<UserVo>> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return Result.ok(userService.search(pageable).map(userMapper::toVo));
    }


    /**
     * 创建用户
     * @param userCreateRequest 请求参数
     * @return 创建用户信息
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result<UserVo> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        UserDto userDto = userService.create(userCreateRequest);
        return Result.ok(userMapper.toVo(userDto));
    }

    /**
     * 根据id 查询当前用户
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    Result<UserVo> get(@PathVariable String id) {
        return Result.ok(userMapper.toVo(userService.get(id)));
    }

    /**
     * 修改用户信息
     * @param id 用户id
     * @param userUpdateRequest 用户信息参数
     * @return 修改成功后的用户信息
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Result<UserVo> update(@PathVariable String id,
                  @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        return Result.ok(userMapper.toVo(userService.update(id, userUpdateRequest)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(@PathVariable String id) {
        userService.delete(id);
    }

    @GetMapping("/me")
    Result<UserVo> me() {
        return Result.ok(userMapper.toVo(userService.getCurrentUser()));
    }


}
