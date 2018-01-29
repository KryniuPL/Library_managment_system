package com.library.model;


import javax.persistence.*;

@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long billID;
    private float fine;
    @ManyToOne(cascade = CascadeType.ALL)
    private User userID;
    @ManyToOne(cascade = CascadeType.ALL)
    private BookBorrow bookBorrowID;


    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public BookBorrow getBookBorrowID() {
        return bookBorrowID;
    }

    public void setBookBorrowID(BookBorrow bookBorrowID) {
        this.bookBorrowID = bookBorrowID;
    }
    public float getFine() {
        return fine;
    }

    public void setFine(float fine) {
        this.fine = fine;
    }
}
