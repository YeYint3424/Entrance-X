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
    private int id;

    @Column(length = 30)
    private String eventName, venue, startTime, endTime;

    private int promotion, standardTicketPrice, standardTicketQuantity, vipTicketPrice, vipTicketQuantity, vvipTicketPrice, vvipTicketQuantity,
            status, standardTicketAvailableQuantity,  vipTicketAvailableQuantity,  vvipTicketAvailableQuantity, trending;
//    0 for pending, 1 for accept and 2 for cancel in status
    private LocalDate date;
    private String eventDescription;
    private LocalDateTime requestTime;

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

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event_Artist> eventArtist = new ArrayList<>();

    public Event() {
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event ID: ").append(id).append("\n");
        sb.append("Event Name: ").append(eventName).append("\n");
        sb.append("Venue: ").append(venue).append("\n");
        sb.append("Start Time: ").append(startTime).append("\n");
        sb.append("End Time: ").append(endTime).append("\n");
        sb.append("Promotion: ").append(promotion).append("\n");
        sb.append("Standard Ticket Price: ").append(standardTicketPrice).append("\n");
        sb.append("Standard Ticket Quantity: ").append(standardTicketQuantity).append("\n");
        sb.append("VIP Ticket Price: ").append(vipTicketPrice).append("\n");
        sb.append("VIP Ticket Quantity: ").append(vipTicketQuantity).append("\n");
        sb.append("VVIP Ticket Price: ").append(vvipTicketPrice).append("\n");
        sb.append("VVIP Ticket Quantity: ").append(vvipTicketQuantity).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Event Description: ").append(eventDescription).append("\n");
        sb.append("Encoded Photo: ").append(encodedPhoto).append("\n");
        sb.append("KPay QR Encoded: ").append(kpayQrEncoded).append("\n");
        sb.append("WavePay QR Encoded: ").append(wavepayQrEncoded).append("\n");
        return sb.toString();
    }


}
