package com.library.scheduler;

import com.library.model.BookBorrow;
import com.library.repository.BookBorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Wykonuje zaplanowane metody na obiektach BookBorrow
 */

@Component
public class BookBorrowScheduler {

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    /**
     * Codziennie o północy sprawdza daty wypożyczeń
     *
     * W tym celu wykorzystuje wzorzec obserwatora,
     * który powiadamia poszczególnych użytkowników
     * o tym, że termin oddania dobiega końca lub został przekroczony
     */
    @Scheduled(cron = "0 0 0 ? * * ")
    public void checkBorrowsDates(){
        List<BookBorrow> bookBorrowList = bookBorrowRepository.findAll();
        for (BookBorrow bookBorrow: bookBorrowList)
            bookBorrow.checkDate();
    }
}
