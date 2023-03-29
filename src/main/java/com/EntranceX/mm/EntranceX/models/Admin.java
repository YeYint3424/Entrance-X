package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Admin Data")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int admin_id;
    private String userName, email, password;




}
