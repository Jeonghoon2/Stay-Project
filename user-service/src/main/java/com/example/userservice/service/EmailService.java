package com.example.userservice.service;

import com.example.userservice.constants.EmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    private final RedisService redisService;
    private final JavaMailSender javaMailSender;
    private final Environment env;

    @Autowired
    public EmailService(RedisService redisService, JavaMailSender javaMailSender, Environment env) {
        this.redisService = redisService;
        this.javaMailSender = javaMailSender;
        this.env = env;
    }


    /* 이메일 인증 코드 발송 */
    public void sendMail(String userEmail){
        SimpleMailMessage message = new SimpleMailMessage();
        String key = createKey();
        message.setTo(userEmail);
        message.setFrom(env.getProperty("secret_settings.address"));
        message.setSubject(EmailConstants.SMTP_EMAIL_CHECK_TITLE_MESSAGE);
        message.setText(EmailConstants.SMTP_EMAIL_CHECK_MESSAGE.replaceAll("\\$key", key));
        javaMailSender.send(message);
        redisService.setDataExpire(userEmail,key,60 * 3L);
    }

    /* 인증번호 만들기 */
    private String createKey() {
        Random random = new Random();
        return Integer.toString(random.nextInt((int)Math.pow(10,6)));
    }
}
