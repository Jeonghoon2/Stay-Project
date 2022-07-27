package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    final RedisService redisService;
    final UserMapper userMapper;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(RedisService redisService, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.redisService = redisService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* 회원가입 */
    public void register(UserDto userDto){
        userDto.setUserUuid(UUID.randomUUID().toString());
        userDto.setUserPassword(bCryptPasswordEncoder.encode(userDto.getUserPassword()));
        userMapper.userRegister(userDto);
    }

    /* 회원가입 - 이메일 중복 확인 */
    public UserDto registerEmailCheck(String email){
        return userMapper.registerEmailCheck(email);
    }

    /* 회원가입 - 닉네임 중복 확인*/
    public UserDto registerNickNameCheck(String nickName){
        return userMapper.registerNickNameCheck(nickName);
    }

    /* 로그인을 위한 User를 새롭게 Builder */
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        UserDto userDto = userMapper.findByEmail(userEmail);
        if(userDto == null){
            throw new UsernameNotFoundException(userEmail);
        }
        return new User(userDto.getUserEmail(), userDto.getUserPassword(),
                true,true,true,true,
                new ArrayList<>());
    }

    /* 이메일을 통해서 유저 상세 정보 불러오기 */
    public UserDto loadByUserId(String userEmail){
        return userMapper.findByEmail(userEmail);
    }

}
