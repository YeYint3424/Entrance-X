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

    private String artistName;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event_Artist> eventArtist = new ArrayList<>();

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", artist='" + artistName + '\'' +
                ", eventArtist=" + eventArtist +
                '}';
    }
}
