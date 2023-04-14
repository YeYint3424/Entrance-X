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
    private int id;

    @Column(length = 30)
    private String userName,email,gender;

    @Column(length = 15)
    private  String phone;

    private String password;
//    one to many from user to order & history
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();

    //one to many from user to watch later
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater = new ArrayList<>();


    public User(int id, String userName, String email, String gender, String phone, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;

    }

    public User() {
    }

    public User(String userName, String email, String gender, String phone, String password) {

        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;

    }
}
