package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity


@Data

@Table(name = "User Data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(length = 30)
    private String name, userName,email,gender,password;

    private LocalDate dateOfBirth;
    private int status;
    @Column(length = 15)
    private  String phone;

//    one to many from user to order & history
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();

    //one to many from user to watch later
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater = new ArrayList<>();


    public User(int user_id, String name, String userName, String email, String gender, String phone, String password, LocalDate dateOfBirth) {
        this.user_id = user_id;
        this.name=name;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public User(String name, String userName, String email, String gender, String phone, String password, LocalDate dateOfBirth) {
        this.name=name;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
