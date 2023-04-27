package com.EntranceX.mm.EntranceX.impli;

import com.EntranceX.mm.EntranceX.config.VerificationCodeGenerator;
import com.EntranceX.mm.EntranceX.dao.EmailVerificationCodeDao;
import com.EntranceX.mm.EntranceX.models.EmailVerificationCode;
import com.EntranceX.mm.EntranceX.services.EmailService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Random;

@Service
public class EmailServiceImpli implements EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    VerificationCodeGenerator verificationCodeGenerator;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailVerificationCodeDao emailVerificationCodeDao;

    @Override
    public EmailVerificationCode sendCode(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String code= verificationCodeGenerator.generateVerificationCode();

        message.setTo(email);
        message.setSubject("Verification Code");
        message.setText("Your verification code is: " + code);
        javaMailSender.send(message);

        EmailVerificationCode emailVerificationCode=new EmailVerificationCode();
        emailVerificationCode.setCode(code);
        emailVerificationCode.setExpiryTime(LocalDateTime.now().plusMinutes(15));
        return emailVerificationCodeDao.save(emailVerificationCode);
    }
}
