package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Admin Data")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int admin_id;
    private String adminName, userName, email, password;




}
