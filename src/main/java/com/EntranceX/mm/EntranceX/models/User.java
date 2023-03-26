package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "User Data")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName,email,gender,phone,password;
    private int dateOfBirth;

//    many to many from user to Event
    @ManyToMany
    @JoinTable(

            joinColumns = { @JoinColumn(name = "User_id") },
            inverseJoinColumns = { @JoinColumn(name = "Event_id") }
    )private List<Event> events= new ArrayList<>();

//    many to one from user to admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> voucher= new ArrayList<>();

}
