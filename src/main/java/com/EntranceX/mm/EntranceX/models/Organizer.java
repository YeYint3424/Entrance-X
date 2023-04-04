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

    @Column(length = 30)
    private String userName, organizerName,  companyName,organizerEmail,companyEmail;


    @Column(length = 15)
    private  String organizerPhone, companyPhone;

    private String companyAddress,companyBio, password;
    private int status;

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

    public int getOrganizer_id() {
        return organizer_id;
    }

    public void setOrganizer_id(int organizer_id) {
        this.organizer_id = organizer_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getOrganizerPhone() {
        return organizerPhone;
    }

    public void setOrganizerPhone(String organizerPhone) {
        this.organizerPhone = organizerPhone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyBio() {
        return companyBio;
    }

    public void setCompanyBio(String companyBio) {
        this.companyBio = companyBio;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
