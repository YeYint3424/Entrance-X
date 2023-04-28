package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "Ticket_Order & History")
public class TicketOrder_History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int standardTicketSold, vipTicketSold,  vvipTicketSold, totalPrice, status;

    @Lob
    @Column(columnDefinition = "longblob")
    private String encodedPaymentScreenShot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    //many to one from order & history to event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "ticketOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketQr> ticketQr = new ArrayList<>();

}



