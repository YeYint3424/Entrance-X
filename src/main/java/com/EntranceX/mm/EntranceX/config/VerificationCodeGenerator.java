package com.EntranceX.mm.EntranceX.config;

import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class VerificationCodeGenerator {

    public static String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return "E-" + code;
    }
}
