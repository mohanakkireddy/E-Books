package com.example.backend.authentication.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "portfolio_user")
public class NewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    private String username;
    private String email;
    private String password;

    public NewUser() {
    }

    public NewUser(int id, String username, String email, String password) {
        Id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "Id=" + Id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
