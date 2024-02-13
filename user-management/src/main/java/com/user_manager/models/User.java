package com.user_manager.models;

import jakarta.persistence.*;

import java.util.Date;

// TODO 1 ~ Make class model to name field table database
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(name = "firstNAME")
    private String firstName;
    @Column(name = "lastNAME")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "createdAT")
    private Date createdAt;
    @Transient  // Make default create Date to 00:00:00
    @Column(name = "updateAT")
    private Date updatedAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
