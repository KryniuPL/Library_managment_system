package com.library.model;

import com.library.config.LibrarySetupConfig;
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

    private Stack<String> notes = new Stack<>();

    public String getNote() {
        if(!notes.empty())
            return notes.pop();
        else
            return null;
    }

    public void setNote(String note) {
        this.notes.push(note);
    }

    public void clearNotifications(){
        this.notes = new Stack<>();
    }
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
     * Ustawia notkę użytkownika w odpowiedzi na zmianę statusu BookBorrow
     *
     * @param o obiekt Observable
     * @param arg argument przekazywany przez notify Observable
     */
    @Override
    public void update(Observable o, Object arg) {
        String msg;
        Object[] status = (Object[])arg;
        Book book = (Book)status[LibrarySetupConfig.OBJECT];
        if ((int)status[LibrarySetupConfig.COMPARISON_RESULT] == LibrarySetupConfig.TERM_REACHED) {
            msg = "Termin przekroczony o " + Math.abs((int) status[LibrarySetupConfig.DAYS_BETWEEN]) + "dni!\n" +
                    "Dotyczy: " + book.getName() + " " + book.getAuthor();
        }
        else {
            msg ="Zostało " + Math.abs((int) status[LibrarySetupConfig.DAYS_BETWEEN]) + " dni!\n" +
                    "Dotyczy: " + book.getName() + " " + book.getAuthor();
        }

        this.setNote(msg);
    }
}





