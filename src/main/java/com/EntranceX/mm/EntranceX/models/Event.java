package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Event Data")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int event_id;
    private String eventName, venue, artist, promotion, paymentMethod, eventDescription, encodedPhoto,time;
    private int shippingCost, standardTicketPrice, standardTicketQuantity, vipTicketPrice, vipTicketQuantity, vvipTicketPrice, vvipTicketQuantity;
    private LocalDate date;


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

    public Event() {
    }

    public Event(int event_id, String eventName, String venue, String artist, String promotion, String paymentMethod, String eventDescription, String encodedPhoto, int shippingCost, int standardTicketPrice, int standardTicketQuantity, int vipTicketPrice, int vipTicketQuantity, int vvipTicketPrice, int vvipTicketQuantity, LocalDate date, String time, byte[] photo) {
        this.event_id = event_id;
        this.eventName = eventName;
        this.venue = venue;
        this.artist = artist;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.eventDescription = eventDescription;
        this.encodedPhoto = encodedPhoto;
        this.shippingCost = shippingCost;
        this.standardTicketPrice = standardTicketPrice;
        this.standardTicketQuantity = standardTicketQuantity;
        this.vipTicketPrice = vipTicketPrice;
        this.vipTicketQuantity = vipTicketQuantity;
        this.vvipTicketPrice = vvipTicketPrice;
        this.vvipTicketQuantity = vvipTicketQuantity;
        this.date = date;
        this.time = time;
        this.photo = photo;
    }

    public Event(String eventName, String venue, String artist, String promotion, String paymentMethod, String eventDescription, String encodedPhoto, int shippingCost, int standardTicketPrice, int standardTicketQuantity, int vipTicketPrice, int vipTicketQuantity, int vvipTicketPrice, int vvipTicketQuantity, LocalDate date, String time, byte[] photo) {
        this.eventName = eventName;
        this.venue = venue;
        this.artist = artist;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.eventDescription = eventDescription;
        this.encodedPhoto = encodedPhoto;
        this.shippingCost = shippingCost;
        this.standardTicketPrice = standardTicketPrice;
        this.standardTicketQuantity = standardTicketQuantity;
        this.vipTicketPrice = vipTicketPrice;
        this.vipTicketQuantity = vipTicketQuantity;
        this.vvipTicketPrice = vvipTicketPrice;
        this.vvipTicketQuantity = vvipTicketQuantity;
        this.date = date;
        this.time = time;
        this.photo = photo;
    }
}
