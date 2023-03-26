package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Watch Later Data")
public class WatchLater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName, eventName;


}
