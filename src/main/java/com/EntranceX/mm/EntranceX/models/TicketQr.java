package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class TicketQr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String ticketType;

    @Lob
    @Column(columnDefinition = "longblob")
    private String ticketQr;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticketOrder_id")
    private TicketOrder_History ticketOrder;

}
