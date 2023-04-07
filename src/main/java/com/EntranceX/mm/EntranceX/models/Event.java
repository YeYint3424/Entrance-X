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

    @Column(length = 30)
    private String eventName, venue, startTime, endTime;

    private int promotion, standardTicketPrice, standardTicketQuantity, vipTicketPrice, vipTicketQuantity, vvipTicketPrice, vvipTicketQuantity, status;
    private LocalDate date;
    private String artist, eventDescription;

    @Lob
    @Column(columnDefinition = "longblob")
    private String encodedPhoto, kpayQrEncoded, wavepayQrEncoded;



//  many to one join with event to organizer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id")
    private  Organizer organizer;
//    one to many from event to order & history;
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();
//  one to many from event to watch later
    @OneToMany(mappedBy = "event",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater= new ArrayList<>();

    public Event() {
    }


}
