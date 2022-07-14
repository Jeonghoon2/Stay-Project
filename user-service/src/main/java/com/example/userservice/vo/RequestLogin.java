package com.example.userservice.vo;

import lombok.Data;

/*
* 로그인을 위한 DATA
* */

@Data
public class RequestLogin {

    private String email;
    private String password;

}
