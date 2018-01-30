package com.library.controllers;

import com.library.config.LibrarySetupConfig;
import com.library.model.Book;
import com.library.model.BookBorrow;
import com.library.model.User;
import com.library.repository.BookBorrowRepository;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Kontroler obsługujący wypożyczenia książek
 */
@Controller
public class BookBorrowController{

    Long tmp;

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    /**
     * Aktualizacja danych książki
     *
     * @return "bookborrowForm" zwraca mapowanie na widok
     */
    @RequestMapping(value = "/bookborrow/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long id, Model model){
        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setBook(bookRepository.findOne(id));
        tmp=id;
        model.addAttribute("bookShow",bookBorrow.getBook());
        model.addAttribute("bookBorrow",bookBorrow);
        return "bookborrowForm";
    }

    /**
     * Wyświetlenie wypożyczonych książek przez użytkownika wybranego z listy
     *
     * @return "borrowedBooks" zwraca mapowanie na widok
     */
    @RequestMapping(value = "/showBorrowedBooks/{id}", method = RequestMethod.GET)
    public String showBorrowedBooks(@PathVariable("id") Long id, Model model)
    {

        System.out.println(id);
        User user1=userRepository.findByUserID(id);


        ArrayList<String> notes=bookBorrowRepository.bookNames(id);
        ArrayList<String> authors=bookBorrowRepository.bookAuthors(id);
        List<BookBorrow> borrowedbooks = bookBorrowRepository.findByUser(user1);
        model.addAttribute("BorrowedBooks",borrowedbooks);
        model.addAttribute("names",notes);
        model.addAttribute("authors",authors);
        System.out.println(authors);
        System.out.println(notes);
        List borrowedBooks=bookBorrowRepository.borrowedBooks(id);
        System.out.println(bookBorrowRepository.borrowedBooks(id));
        System.out.println(borrowedBooks);

        return "borrowedBooks";
    }

    /**
     * Wyświetlenie widoku umożliwiającego oddanie ksiązki
     *
     * @return "borrowedBooks" zwraca mapowanie na widok
     */
    @RequestMapping(value = "/giveback/{id}", method = RequestMethod.GET)
    public String giveback(@PathVariable("id") Long id, Model model)
    {
        BookBorrow  requestedBook= new BookBorrow();
        requestedBook.setBook(bookRepository.findOne(id));
        tmp=id;
        model.addAttribute("bookShow",requestedBook.getBook());
        model.addAttribute("bookBorrow",requestedBook);
        return "givebackForm";
    }

    /**
     * Oddanie książki
     *
     * @return "allbooks" zwraca mapowanie na widok
     */
    @RequestMapping(value = "/giveback/save", method = RequestMethod.POST)
    public String saveGiveBack(@Valid @ModelAttribute("bookborrow") BookBorrow bookBorrow, @Valid @ModelAttribute("username")String username, Model model)
    {
        Long bookID=bookBorrowRepository.findBorrowIDByBookID(tmp);
        bookBorrowRepository.delete(bookID);
        bookRepository.updateStatus("free",tmp);
        return "redirect:/allbooks";
    }


    /**
     * Zapisanie wypożyczenia książki
     *
     * @return "redirect:/allbooks" przekierowuje na widok /allbooks
     */
    @RequestMapping(value = "/bookborrow/save", method = RequestMethod.POST)
    public String saveBorrowBook(@Valid @ModelAttribute("bookborrow") BookBorrow bookBorrow, @Valid @ModelAttribute("username")String username, Model model)
    {
        Date date = new Date();
        bookBorrow.setStartDate(date);
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC +2"));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, LibrarySetupConfig.BORROW_TIME_DAYS);
        date = calendar.getTime();
        bookBorrow.setEndDate(date);
        User user=userService.findByUsername(username);
        Book bookname=bookRepository.findByBookID(tmp);
        bookBorrow.setUser(user);
        bookBorrow.setBook(bookname);


        bookBorrow.addObserver(user);

        bookBorrowRepository.save(bookBorrow);
        bookRepository.updateStatus("issued",tmp);
        return "redirect:/allbooks";
    }
}
