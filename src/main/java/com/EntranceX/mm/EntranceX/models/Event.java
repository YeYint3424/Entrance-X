package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Event Data")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String eventName,time,date,place,singer,ticketType,ticketLimit,price,promotion,paymentMethod,eventDescription;

    public Event() {
    }

    public Event(String eventName, String time, String date, String place, String singer, String ticketType, String ticketLimit, String price, String promotion, String paymentMethod, String eventDescription) {
        this.eventName = eventName;
        this.time = time;
        this.date = date;
        this.place = place;
        this.singer = singer;
        this.ticketType = ticketType;
        this.ticketLimit = ticketLimit;
        this.price = price;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.eventDescription = eventDescription;
    }

    public Event(int id, String eventName, String time, String date, String place, String singer, String ticketType, String ticketLimit, String price, String promotion, String paymentMethod, String eventDescription) {
        this.id = id;
        this.eventName = eventName;
        this.time = time;
        this.date = date;
        this.place = place;
        this.singer = singer;
        this.ticketType = ticketType;
        this.ticketLimit = ticketLimit;
        this.price = price;
        this.promotion = promotion;
        this.paymentMethod = paymentMethod;
        this.eventDescription = eventDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketLimit() {
        return ticketLimit;
    }

    public void setTicketLimit(String ticketLimit) {
        this.ticketLimit = ticketLimit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
