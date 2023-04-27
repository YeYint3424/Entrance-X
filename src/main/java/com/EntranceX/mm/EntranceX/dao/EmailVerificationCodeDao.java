package com.EntranceX.mm.EntranceX.dao;

import com.EntranceX.mm.EntranceX.models.EmailVerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailVerificationCodeDao extends JpaRepository<EmailVerificationCode, Integer> {
}
