package com.EntranceX.mm.EntranceX.services;

import com.EntranceX.mm.EntranceX.models.EmailVerificationCode;

public interface EmailService {
    EmailVerificationCode sendCode(String email);

}
