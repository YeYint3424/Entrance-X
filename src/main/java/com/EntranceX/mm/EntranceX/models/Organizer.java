package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Organizer Data")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String organizerName,companyName,organizerEmail,companyEmail,companyPhone,companyAddress,companyBio,password;

//  one to many from Organizer to Event
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events= new ArrayList<>();

//    many to one from organizer to admin
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Admin_id")
    private Admin admin;


}
