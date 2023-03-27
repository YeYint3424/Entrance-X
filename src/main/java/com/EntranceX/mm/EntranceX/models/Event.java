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
    private int event_id;
    private String eventName, time, venue, artist, promotion, paymentMethod, eventDescription, encodedPhoto;
    private int shippingCost, standardTicketPrice, standardTicketQuantity, VipTicketPrice, VipTicketQuantity, VVipTicketPrice, VVipTicketQuantity, date;

    @Lob
    private byte[] photo;



//  many to one join with event to organizer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private  Organizer organizer;

//    one to many from event to order & history;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();
//one to many from event to watch later
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater= new ArrayList<>();



}
