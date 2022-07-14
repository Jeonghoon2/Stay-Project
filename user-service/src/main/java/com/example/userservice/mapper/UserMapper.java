package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    void userRegister(UserDto userDto);

    UserDto registerEmailCheck(String email);

    UserDto registerNickNameCheck(String nickName);

    UserDto findByEmail(String email);
}
