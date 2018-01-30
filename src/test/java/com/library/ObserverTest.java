package com.library;

import com.library.model.BookBorrow;
import com.library.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Won't pass since update method on Observer changed
 */
public class ObserverTest
{
    BookBorrow bookBorrow = new BookBorrow();
    User user = new User();

    @Test
    public void observerTestTermInWeek(){

        System.out.println("Termin w przeciagu tygodnia");

        bookBorrow.addObserver(user);
        bookBorrow.setEndDate(new GregorianCalendar(2018,1,1).getTime());
        bookBorrow.checkDate();

        System.out.println("Ustawiona wiadomosc: " + user.getNote());
        System.out.println("Dzisiaj: " + new Date());
        System.out.println("Koniec terminu: " + bookBorrow.getEndDate());
        System.out.println("Roznica dni: " + ChronoUnit.DAYS.between(new Date().toInstant(),bookBorrow.getEndDate().toInstant()));


        Assert.assertEquals(user.getNote(),"Tydzień lub mniej do końca");
    }

    @Test
    public void observerTestTermPassed(){

        System.out.println("Termin przekroczony");

        bookBorrow.addObserver(user);
        bookBorrow.setEndDate(new GregorianCalendar(2018,0,10).getTime());
        bookBorrow.checkDate();

        System.out.println("Ustawiona wiadomosc: " + user.getNote());
        System.out.println("Dzisiaj: " + new Date());
        System.out.println("Koniec terminu: " + bookBorrow.getEndDate());
        System.out.println("Roznica dni: " + ChronoUnit.DAYS.between(new Date().toInstant(),bookBorrow.getEndDate().toInstant()));

        Assert.assertEquals(user.getNote(),"Termin przekroczony");
    }
}
