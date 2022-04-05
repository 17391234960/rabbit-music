package com.skyblue.rabbitmusic.mapper;

import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.dto.request.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.request.UserUpdateRequest;
import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toDTO(User user);

    UserVo toVo(UserDto userDto);

    User createEntity(UserCreateRequest userCreateRequest);

    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);
}
