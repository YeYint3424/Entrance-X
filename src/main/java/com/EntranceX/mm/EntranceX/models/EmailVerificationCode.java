package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class EmailVerificationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private LocalDateTime expiryTime;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
