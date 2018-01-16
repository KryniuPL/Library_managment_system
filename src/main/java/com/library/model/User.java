package com.library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class User{


    private Integer userID;
    private String firstname;
    private String surname;
    private String username;
    private String password;
    private String email;
    private Integer enabled;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}





