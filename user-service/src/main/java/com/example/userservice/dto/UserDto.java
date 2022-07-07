package com.example.userservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String userUuid;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userProfileImage;
    private String userNickName;
    private LocalDate userBirth;

}
