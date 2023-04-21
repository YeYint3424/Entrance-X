package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class TicketQr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Lob
    @Column(columnDefinition = "longblob")
    private String encodedSD1, encodedSD2, encodedSD3, encodedSD4, encodedSD5,
            encodedVIP1, encodedVIP2, encodedVIP3, encodedVIP4, encodedVIP5,
            encodedVVIP1, encodedVVIP2, encodedVVIP3, encodedVVIP4, encodedVVIP5;

}
