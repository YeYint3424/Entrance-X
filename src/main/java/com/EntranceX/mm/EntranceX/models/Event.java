package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Event Data")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String eventName,time,place,artist,ticketType,ticketLimit,price,promotion,shippingCost, paymentMethod,eventDescription,encodedPhoto;
    private int date;

    @Lob
    private byte[] photo;

//  many to many from event to user
    @ManyToMany(mappedBy = "events")
    private List<User> user= new ArrayList<>();

//  many to one join with event to organizer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Organizer_id")
    private  Organizer organizer;

//    many to one join with event to admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voucher> voucher= new ArrayList<>();



}
