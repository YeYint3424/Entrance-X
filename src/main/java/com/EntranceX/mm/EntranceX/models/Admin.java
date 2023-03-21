package com.EntranceX.mm.EntranceX.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Admin Data")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName,lastName,userName,email,password;
    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> event= new ArrayList<>();
    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> user= new ArrayList<>();
    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Organizer> organizer= new ArrayList<>();
    public Admin() {

    }

    public Admin(String firstName, String lastName, String userName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public Admin(int id, String firstName, String lastName, String userName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
