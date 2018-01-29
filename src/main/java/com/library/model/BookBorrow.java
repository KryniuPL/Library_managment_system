package com.library.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Odwzorowuje tabelę wypożyczenia z bazy danych
 */
@Entity
public class BookBorrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long borrowID;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Book book;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Long getBorrowID() {
        return borrowID;
    }

    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


}
