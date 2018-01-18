package com.library.model;


import javax.persistence.*;
import java.util.Date;

@Entity
public class BookBorrow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long borrowID;
    private Long userID;
    private Long bookID;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Long getBookID() {

        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public Long getUserID() {

        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getBorrowID() {

        return borrowID;
    }

    public void setBorrowID(Long borrowID) {
        this.borrowID = borrowID;
    }
}
