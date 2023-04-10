package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data

@Table(name = "Organizer Data")
public class  Organizer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30)
    private String userName, organizerName,  companyName,organizerEmail,companyEmail;


    @Column(length = 15)
    private  String organizerPhone, companyPhone,role;

    private String companyAddress,companyBio, password;
    private int status;


//  one to many from Organizer to Event
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events= new ArrayList<>();

    public Organizer(int id, String userName, String organizerName, String companyName, String organizerEmail, String companyEmail, String organizerPhone, String companyPhone, String companyAddress, String companyBio, String password, int status) {
        this.id = id;
        this.userName = userName;
        this.organizerName = organizerName;
        this.companyName = companyName;
        this.organizerEmail = organizerEmail;
        this.companyEmail = companyEmail;
        this.organizerPhone = organizerPhone;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.companyBio = companyBio;
        this.password = password;
        this.status = status;

    }

    public Organizer(String userName, String organizerName, String companyName, String organizerEmail, String companyEmail, String organizerPhone, String companyPhone, String companyAddress, String companyBio, String password, int status) {
        this.userName = userName;
        this.organizerName = organizerName;
        this.companyName = companyName;
        this.organizerEmail = organizerEmail;
        this.companyEmail = companyEmail;
        this.organizerPhone = organizerPhone;
        this.companyPhone = companyPhone;
        this.companyAddress = companyAddress;
        this.companyBio = companyBio;
        this.password = password;
        this.status = status;

    }

    public Organizer() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Organizer ID: ").append(id).append("\n");
        sb.append("User Name: ").append(userName).append("\n");
        sb.append("Organizer Name: ").append(organizerName).append("\n");
        sb.append("Company Name: ").append(companyName).append("\n");
        sb.append("Organizer Email: ").append(organizerEmail).append("\n");
        sb.append("Company Email: ").append(companyEmail).append("\n");
        sb.append("Organizer Phone: ").append(organizerPhone).append("\n");
        sb.append("Company Phone: ").append(companyPhone).append("\n");
        sb.append("Company Address: ").append(companyAddress).append("\n");
        sb.append("Company Bio: ").append(companyBio).append("\n");
        sb.append("Password: ").append(password).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("Events:\n");
        for (Event event : events) {
            sb.append(event).append("\n");
        }
        return sb.toString();
    }





}
