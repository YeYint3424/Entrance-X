package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String artist;

    @OneToMany(mappedBy = "artist")
    private List<Event_Artist> eventArtist = new ArrayList<>();
}
