package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user-service")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/health_check")
    public String health_check(){
        return "hi";
    }


    /* 회원가입 */
    @PostMapping("/register")
    public ResponseEntity userRegister(@RequestBody UserDto userDto){
        userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입이 완료 되었습니다.");
    }

    /* 이메일 중복 확인 */
    /* 이메일 중복 확인 -> 이메일 인증을 위한 이메일 보내기 -> 이메일 인증*/
    @GetMapping("/register/check/email/{email}")
    public ResponseEntity registerEmailCheck(
            @PathVariable("email")String email){
        UserDto userDto = userService.registerEmailCheck(email);

        if (userDto == null){
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 이메일입니다.");

        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 이메일입니다.");
        }
    }

    /* 닉네임 중복 확인 */
    @GetMapping("/register/check/nickname/{nickname}")
    public ResponseEntity registerNicknameCheck(
            @PathVariable("nickname")String nickname){
        UserDto userDto = userService.registerNickNameCheck(nickname);
        if (userDto == null){
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임입니다.");
        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 닉네임입니다.");
        }
    }
}
