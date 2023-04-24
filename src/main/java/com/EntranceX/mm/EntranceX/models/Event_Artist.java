package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event_Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
