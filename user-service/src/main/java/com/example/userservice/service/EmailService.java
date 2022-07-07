package com.example.userservice.service;

import com.example.userservice.constants.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    private final RedisService redisService;
    private  JavaMailSender mailSender;

    @Autowired
    public EmailService(RedisService redisService) {
        this.redisService = redisService;
    }

    /* 이메일 인증 코드 발송 */
    public void sendMail(String userEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        String key = createKey();
        message.setTo(userEmail);
        message.setFrom(EmailConstants.SMTP_ADDRESS);
        message.setSubject(EmailConstants.SMTP_EMAIL_CHECK_TITLE_MESSAGE);
        message.setText(EmailConstants.SMTP_EMAIL_CHECK_TITLE_MESSAGE.replaceAll("\\$key", key));
        redisService.setDataExpire(userEmail,key,60 * 3L);
        mailSender.send(message);
    }

    /* 인증번호 만들기 */
    private String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }
}
