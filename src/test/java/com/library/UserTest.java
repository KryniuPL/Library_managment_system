package com.library;

import com.library.config.LibrarySetupConfig;
import com.library.model.Book;
import com.library.model.BookBorrow;
import com.library.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Observable;

public class UserTest {

    private User user = new User();

    @Test
    public void updateAndStringStackTest(){
        Object[] objects = new Object[3];
        Book book = new Book();
        book.setName("test 1");
        book.setAuthor("test 2");

        Observable observable = new Observable();

        objects[LibrarySetupConfig.COMPARISON_RESULT] = LibrarySetupConfig.TERM_REACHED;
        objects[LibrarySetupConfig.DAYS_BETWEEN] = -10;
        objects[LibrarySetupConfig.OBJECT] = book;

        user.update(observable,objects);
        Assert.assertEquals(user.getNote(),"Termin przekroczony o 10dni!\n" +
                "Dotyczy: test 1 test 2");

        user.update(observable,objects);
        user.update(observable,objects);
        Assert.assertEquals(user.getNote(),"Termin przekroczony o 10dni!\n" +
                "Dotyczy: test 1 test 2");
        Assert.assertEquals(user.getNote(),"Termin przekroczony o 10dni!\n" +
                "Dotyczy: test 1 test 2");

    }

}
