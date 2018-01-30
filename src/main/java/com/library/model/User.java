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
    @NotEmpty(message = "*Pole imię nie może być puste")
    private String firstname;
    @NotEmpty(message = "*Pole nazwisko nie może być puste")
    private String surname;
    @NotEmpty(message = "*Pole login nie może być puste")
    private String username;
    @NotEmpty(message = "*Pole hasło nie może być puste")
    private String password;
    @Email(message = "*Podaj prawidłowy adres e-mail")
    @NotEmpty(message = "*Pole e-mail nie może być puste")
    private String email;
    private int active;
    private Set<Role> roles = new HashSet<>(0);

    private String note;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        if ((int)arg == -1) //Termin przekroczony
            this.setNote("Termin przekroczony");
        else
            this.setNote("Tydzień lub mniej do końca"); //Tydzień lub mniej do końca
    }
}





