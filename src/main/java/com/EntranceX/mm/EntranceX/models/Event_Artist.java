package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event_Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Override
    public String toString() {
        return "Event_Artist{" +
                "id=" + id +
                ", artist=" + artist +
                ", event=" + event +
                '}';
    }
}
