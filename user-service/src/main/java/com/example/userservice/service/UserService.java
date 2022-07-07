package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserService {
    final RedisService redisService;
    final UserMapper userMapper;

    @Autowired
    public UserService(RedisService redisService, UserMapper userMapper) {
        this.redisService = redisService;
        this.userMapper = userMapper;
    }

    /* 회원가입 */
    public void register(UserDto userDto){
        userDto.setUserUuid(UUID.randomUUID().toString());
        userMapper.userRegister(userDto);
    }

    /* 이메일 중복 확인 */
    public UserDto registerEmailCheck(String email){
        return userMapper.registerEmailCheck(email);
    }

    public UserDto registerNickNameCheck(String nickName){
        return userMapper.registerNickNameCheck(nickName);
    }
}
