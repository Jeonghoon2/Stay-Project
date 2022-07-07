package com.example.userservice.controller;

import com.example.userservice.dto.UserDto;
import com.example.userservice.service.EmailService;
import com.example.userservice.service.RedisService;
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

    private final RedisService redisService;
    private final UserService userService;
    private final EmailService emailService;

    @Autowired
    public UserController(RedisService redisService, UserService userService, EmailService emailService) {
        this.redisService = redisService;
        this.userService = userService;
        this.emailService = emailService;
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
    @GetMapping("/register/check/email/{email}")
    public ResponseEntity registerEmailCheck(
            @PathVariable("email")String email) throws NullPointerException{
        UserDto userDto = userService.registerEmailCheck(email);

        if (userDto == null){
            emailService.sendMail(email);
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 이메일입니다.\n 인증코드가 발송 되었습니다.");

        }else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("사용중인 이메일입니다.");
        }
    }


    /* 이메일 인증 코드 확인 */
    @GetMapping("/register/check/email/{email}/{code}")
    public ResponseEntity emailCheck(@PathVariable("email") String email,
                                     @PathVariable("code") String code){
        String valueKey = redisService.getData(email);
        log.info(valueKey);
        if (Integer.parseInt(valueKey) == Integer.parseInt(code)){
            redisService.deleteData(email);
            return ResponseEntity.status(HttpStatus.OK).body("이메일 인증이 완료 되었습니다.");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 인증 코드 입니다.");
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
