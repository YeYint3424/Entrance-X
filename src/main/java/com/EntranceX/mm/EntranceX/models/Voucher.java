package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Voucher & History")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String eventname, date, time, venue, type, quantity, promotion, payment;
    private String username, email, phone;
    private  int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Event_id")
    private Event event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Voucher(int id, String eventname, String date, String time, String venue, String type, String quantity, String promotion, String payment, String username, String email, String phone, int price) {
        this.id = id;
        this.eventname = eventname;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.type = type;
        this.quantity = quantity;
        this.promotion = promotion;
        this.payment = payment;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.price = price;
    }

    public Voucher(String eventname, String date, String time, String venue, String type, String quantity, String promotion, String payment, String username, String email, String phone, int price) {
        this.eventname = eventname;
        this.date = date;
        this.time = time;
        this.venue = venue;
        this.type = type;
        this.quantity = quantity;
        this.promotion = promotion;
        this.payment = payment;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.price = price;
    }

    public Voucher() {
    }
}



