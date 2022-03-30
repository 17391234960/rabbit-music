package com.skyblue.rabbitmusic.repository;

import com.skyblue.rabbitmusic.entity.User;
import com.skyblue.rabbitmusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser() {
        User user = new User();
        user.setUsername("兔子");
        user.setNickname("兔子音乐");
        user.setLocked(false);
        user.setEnabled(true);
        user.setGender(Gender.MALE);
        user.setLastLoginIp("127.0.0.1");
        user.setLastLoginTime(new Date());
        User save = userRepository.save(user);
        System.out.println(save);
        User username = userRepository.findUserByUsername("兔子");
        System.out.println(username);
    }

    @Test
    public void findUsername() {

    }
}