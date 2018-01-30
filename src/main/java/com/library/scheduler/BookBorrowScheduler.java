package com.library.scheduler;

import com.library.model.BookBorrow;
import com.library.repository.BookBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableScheduling
public class BookBorrowScheduler {

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Scheduled(cron = "0 0 0 1/1 * ? *")
    public void checkBorrows(){
        List<BookBorrow> bookBorrowList = bookBorrowRepository.findAll();
        for (BookBorrow bookBorrow: bookBorrowList)
            bookBorrow.checkDate();
    }

}
