package com.library.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.*;

/**
 * Odwzorowuje użytkownika z bazy danych
 */
@Entity
public class User implements Observer {

    private Long userID;
    @NotEmpty(message = "*Please provide your firstname")
    private String firstname;
    @NotEmpty(message = "*Please provide your lastname")
    private String surname;
    @NotEmpty(message = "*Please provide your login name")
    private String username;
    @NotEmpty(message = "*Please provide your password")
    private String password;
    @Email(message = "*Please provide a valid email")
    @NotEmpty(message = "*Please provide an email")
    private String email;
    private int active;
    private Set<Role> roles = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    /**
     * Obserwator PART II
     *
     * Myśleć co chemy z tym zrobić :)
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {

    }
}





