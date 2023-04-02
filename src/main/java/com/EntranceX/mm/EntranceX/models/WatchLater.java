package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "Watch Later Data")
public class WatchLater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int watchLater_id;


}
