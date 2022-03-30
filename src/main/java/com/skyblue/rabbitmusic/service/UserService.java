package com.skyblue.rabbitmusic.service;

import com.skyblue.rabbitmusic.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> listAll();
}
