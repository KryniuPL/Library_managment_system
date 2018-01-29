package com.library;

import com.library.model.BookBorrow;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookBorrowTest {

    private BookBorrow bookBorrow = new BookBorrow();

    // Time is always compared to current

    @Test
    public void testDataComparatorSmaller(){

        bookBorrow.setEndDate(new GregorianCalendar(2018,0,20).getTime());
        Date currDate = new Date();
        System.out.println(currDate);
        System.out.println(bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(bookBorrow.getEndDate().toInstant(), currDate.toInstant()));

        Assert.assertEquals(bookBorrow.compareDate(),-1);

    }

    @Test
    public void testDataComparatorBigger(){

        bookBorrow.setEndDate(new GregorianCalendar(2018,2,30).getTime());
        Date currDate = new Date();
        System.out.println(currDate);
        System.out.println(bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(bookBorrow.getEndDate().toInstant(), currDate.toInstant()));

        Assert.assertEquals(bookBorrow.compareDate(),1);

    }

    @Test
    public void testDataComparatorWeek(){

        bookBorrow.setEndDate(new GregorianCalendar(2018,0,25).getTime());
        Date currDate = new Date();
        System.out.println(currDate);
        System.out.println(bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(bookBorrow.getEndDate().toInstant(), currDate.toInstant()));

        Assert.assertEquals(bookBorrow.compareDate(),2);


    }

}
