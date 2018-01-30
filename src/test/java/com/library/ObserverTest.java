package com.library;

import com.library.model.Book;
import com.library.model.BookBorrow;
import com.library.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * CHECK DATES BEFORE RUNNING
 */
public class ObserverTest
{
    BookBorrow bookBorrow = new BookBorrow();
    User user = new User();
    Book book = new Book();

    @Test
    public void observerTestTermInWeek(){

        book.setName("test");
        book.setAuthor("test");

        System.out.println("Termin w przeciagu tygodnia");

        bookBorrow.setBook(book);
        bookBorrow.addObserver(user);
        bookBorrow.setEndDate(new GregorianCalendar(2018,1,1).getTime());
        bookBorrow.checkDate();

        System.out.println("Ustawiona wiadomosc: " + user.getNote());

        bookBorrow.checkDate();

        System.out.println("Dzisiaj: " + new Date());
        System.out.println("Koniec terminu: " + bookBorrow.getEndDate());
        System.out.println("Roznica dni: " + ChronoUnit.DAYS.between(new Date().toInstant(),bookBorrow.getEndDate().toInstant()));


        Assert.assertEquals(user.getNote(),"Zostało 1 dni!\n" +
                "Dotyczy: test test");
    }

    @Test
    public void observerTestTermPassed(){

        book.setName("test1");
        book.setAuthor("test1");

        System.out.println("Termin przekroczony");

        bookBorrow.setBook(book);
        bookBorrow.addObserver(user);
        bookBorrow.setEndDate(new GregorianCalendar(2018,0,10).getTime());
        bookBorrow.checkDate();

        System.out.println("Ustawiona wiadomosc: " + user.getNote());
        System.out.println("Dzisiaj: " + new Date());
        System.out.println("Koniec terminu: " + bookBorrow.getEndDate());

        bookBorrow.checkDate();

        System.out.println("Roznica dni: " + ChronoUnit.DAYS.between(new Date().toInstant(),bookBorrow.getEndDate().toInstant()));

        Assert.assertEquals(user.getNote(),"Termin przekroczony o 20dni!\n" +
                "Dotyczy: test1 test1");
    }
}
