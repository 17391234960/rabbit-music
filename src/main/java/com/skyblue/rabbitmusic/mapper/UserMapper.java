package com.skyblue.rabbitmusic.mapper;

import com.skyblue.rabbitmusic.dto.UserCreateRequest;
import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.vo.UserVo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    UserDto toDTO(User user);

    UserVo toVo(UserDto userDto);

    User toEntity(UserCreateRequest userCreateRequest);
}
