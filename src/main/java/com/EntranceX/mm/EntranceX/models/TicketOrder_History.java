package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "Ticket_Order & History")
public class TicketOrder_History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;

    private int standardTicketSold, VipTicketQuantitySold,  VVipTicketQuantitySold, totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;


}



