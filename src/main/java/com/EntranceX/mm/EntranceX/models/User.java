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
    private int user_id;
    private String userName,email,gender,phone,password;
    private int dateOfBirth;

    //one to many from user to order & history
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();

    //one to many from user to watch later
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater = new ArrayList<>();
}
