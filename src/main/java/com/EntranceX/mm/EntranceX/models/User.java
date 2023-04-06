package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;
import lombok.*;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity


@Data

@Table(name = "User Data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(length = 30)
    private String name, userName,email,gender;

    private LocalDate dateOfBirth;
    private int status;
    @Column(length = 15)
    private  String phone, role;

    private String password;
//    one to many from user to order & history
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketOrder_History> orderHistory= new ArrayList<>();

    //one to many from user to watch later
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WatchLater> watchLater = new ArrayList<>();

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<TicketOrder_History> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<TicketOrder_History> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<WatchLater> getWatchLater() {
        return watchLater;
    }

    public void setWatchLater(List<WatchLater> watchLater) {
        this.watchLater = watchLater;
    }

    public User(int user_id, String name, String userName, String email, String gender, String phone, String password, LocalDate dateOfBirth) {
        this.user_id = user_id;
        this.name=name;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {
    }

    public User(String name, String userName, String email, String gender, String phone, String password, LocalDate dateOfBirth) {
        this.name=name;
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
}
