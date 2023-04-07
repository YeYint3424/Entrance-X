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
    private  String organizerPhone, companyPhone,role;

    private String companyAddress,companyBio, password;
    private int status;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] kpayQr, wavepayQr, kpayQrEncoded, wavepayQrEncoded;

//  one to many from Organizer to Event
    @OneToMany(mappedBy = "organizer",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events= new ArrayList<>();

    public Organizer(int organizer_id, String userName, String organizerName, String companyName, String organizerEmail, String companyEmail, String organizerPhone, String companyPhone, String companyAddress, String companyBio, String password, int status, byte[] kpayQr, byte[] wavepayQr, byte[] kpayQrEncoded, byte[] wavepayQrEncoded) {
        this.organizer_id = organizer_id;
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
        this.kpayQr = kpayQr;
        this.wavepayQr = wavepayQr;
        this.kpayQrEncoded = kpayQrEncoded;
        this.wavepayQrEncoded = wavepayQrEncoded;
    }

    public Organizer(String userName, String organizerName, String companyName, String organizerEmail, String companyEmail, String organizerPhone, String companyPhone, String companyAddress, String companyBio, String password, int status, byte[] kpayQr, byte[] wavepayQr, byte[] kpayQrEncoded, byte[] wavepayQrEncoded) {
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
        this.kpayQr = kpayQr;
        this.wavepayQr = wavepayQr;
        this.kpayQrEncoded = kpayQrEncoded;
        this.wavepayQrEncoded = wavepayQrEncoded;
    }

    public Organizer() {
    }
}
