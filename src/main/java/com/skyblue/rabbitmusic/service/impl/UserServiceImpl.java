package com.skyblue.rabbitmusic.service.impl;

import com.skyblue.rabbitmusic.dto.UserDto;
import com.skyblue.rabbitmusic.mapper.UserMapper;
import com.skyblue.rabbitmusic.repository.UserRepository;
import com.skyblue.rabbitmusic.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    @Override
    public List<UserDto> listAll() {
        return userRepository.findAll()
                .stream().map(this.userMapper::toDTO).collect(Collectors.toList());
    }

}
