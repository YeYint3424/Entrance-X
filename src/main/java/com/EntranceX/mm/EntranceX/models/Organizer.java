package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data

@Table(name = "Organizer Data")
public class Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int organizer_id;
    private String userName, organizerName, organizerPhone, companyName,organizerEmail,companyEmail,companyPhone, companyAddress,companyBio,password;


//  one to many from Organizer to Event
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events= new ArrayList<>();

    public Organizer() {
    }

    public Organizer(int organizer_id, String userName, String organizerName, String organizerPhone, String companyName, String organizerEmail, String companyEmail, String companyPhone, String companyAddress, String companyBio, String password) {
        this.organizer_id = organizer_id;
        this.userName = userName;
        this.organizerName = organizerName;
        this.organizerPhone = organizerPhone;
        this.companyName = companyName;
        this.organizerEmail = organizerEmail;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.companyBio = companyBio;
        this.password = password;
    }

    public Organizer(String userName, String organizerName, String companyName, String organizerEmail, String organizerPhone, String companyEmail, String companyPhone, String companyAddress, String companyBio, String password) {
        this.organizerName = userName;
        this.organizerName = organizerName;
        this.companyName = companyName;
        this.organizerEmail = organizerEmail;
        this.organizerPhone = organizerPhone;
        this.companyEmail = companyEmail;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.companyBio = companyBio;
        this.password = password;
    }
}
