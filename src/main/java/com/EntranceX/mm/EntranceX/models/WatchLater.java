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
    private int watchLater_id;

//    many to one from watch later to event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private  Event event;
//    many to one from watch later to user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private  User user;

}
