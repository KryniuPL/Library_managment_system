package com.library;

import com.library.config.LibrarySetupConfig;
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

        System.out.println("Smaller");

        bookBorrow.setEndDate(new GregorianCalendar(2018,0,20).getTime());
        Date currDate = new Date();
        System.out.println("Dzisiaj: " + currDate);
        System.out.println("Data oddania: " + bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(currDate.toInstant(), bookBorrow.getEndDate().toInstant()));

        Assert.assertEquals(bookBorrow.compareDate()[LibrarySetupConfig.COMPARISON_RESULT],-1);

    }

    @Test
    public void testDataComparatorBigger(){

        System.out.println("Bigger");

        bookBorrow.setEndDate(new GregorianCalendar(2018,2,30).getTime());
        Date currDate = new Date();
        System.out.println("Dzisiaj: " + currDate);
        System.out.println("Data oddania: " + bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(currDate.toInstant(), bookBorrow.getEndDate().toInstant()));

        Assert.assertEquals(bookBorrow.compareDate()[LibrarySetupConfig.COMPARISON_RESULT],1);

    }

    @Test
    public void testDataComparatorWeek(){

        System.out.println("Week");

        bookBorrow.setEndDate(new GregorianCalendar(2018,1,4).getTime());
        Date currDate = new Date();
        System.out.println("Dzisiaj: " + currDate);
        System.out.println("Data oddania: " + bookBorrow.getEndDate());
        System.out.println(ChronoUnit.DAYS.between(currDate.toInstant(), bookBorrow.getEndDate().toInstant()));

        Assert.assertEquals(bookBorrow.compareDate()[LibrarySetupConfig.COMPARISON_RESULT],2);


    }

}
