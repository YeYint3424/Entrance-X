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

    @OneToOne
    @JoinColumn(name = "TicketOrder_id", referencedColumnName = "id")
    private TicketOrder_History ticketOrder;

}
