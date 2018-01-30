package com.library.model;


import com.library.config.LibrarySetupConfig;

import javax.persistence.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Observable;

/**
 * Odwzorowuje tabelę wypożyczenia z bazy danych
 */
@Entity
public class BookBorrow extends Observable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long borrowID;

    @ManyToOne
    private User user;
    @ManyToOne
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


    /**
     * Porównuje datę bierzącą z datą oddania i odpowiednio ustawia tablicę status
     *
     * @return status - tablica z wynikiem powrównania, różnicą dni i obiektem książki
     */
    public Object[] compareDate(){
        Object[] status = new Object[3];
        Date currDate = new Date();
        Long daysBetween = ChronoUnit.DAYS.between(currDate.toInstant(), endDate.toInstant());
        status[LibrarySetupConfig.DAYS_BETWEEN] = daysBetween.intValue();
        status[LibrarySetupConfig.OBJECT] = this.getBook();
        if (daysBetween <= LibrarySetupConfig.BORROW_NOTIFICATION_DAYS && daysBetween >= 0) {
            status[LibrarySetupConfig.COMPARISON_RESULT] = LibrarySetupConfig.TERM_SOON;
            return status;
        }
        else
            status[LibrarySetupConfig.COMPARISON_RESULT] = endDate.compareTo(currDate);
            return status;
    }

    /**
     * Sprawdzenie wyniku porównania i wywołanie changeUpdate
     */
    public void checkDate(){
        Object[] result = compareDate();
        if ((int)result[LibrarySetupConfig.COMPARISON_RESULT] == LibrarySetupConfig.TERM_REACHED
                || (int) result[LibrarySetupConfig.COMPARISON_RESULT] == LibrarySetupConfig.TERM_SOON)
            changeUpdate(result);
    }

    /**
     * Zamiana stanu Observable i powiadomienie obserwujących
     *
     * @param status tablica z wynikiem powrównania, różnicą dni i obiektem książki
     */
    private void changeUpdate(Object status[]){
        setChanged();
        notifyObservers(status);
    }



}
