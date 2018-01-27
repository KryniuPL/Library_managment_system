package com.library.controllers;

import com.library.model.Book;
import com.library.model.BookBorrow;
import com.library.model.User;
import com.library.repository.BookBorrowRepository;
import com.library.repository.BookRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Controller
public class BookBorrowController{


    Long tmp;
    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/bookborrow/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long id, Model model){
        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setBook(bookRepository.findOne(id));
        tmp=id;
        model.addAttribute("bookShow",bookBorrow.getBook());
        model.addAttribute("bookBorrow",bookBorrow);
        return "bookborrowForm";
    }

    @RequestMapping(value = "/bookborrow/save", method = RequestMethod.POST)
    public String saveBorrowBook(@Valid @ModelAttribute("bookborrow") BookBorrow bookBorrow, @Valid @ModelAttribute("username")String username, Model model)
    {
        Date date = new Date();
        bookBorrow.setStartDate(date);
        Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("UTC +2"));
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 90);
        date = calendar.getTime();
        bookBorrow.setEndDate(date);
        User user=userService.findByUsername(username);
        Book bookname=bookRepository.findByBookID(tmp);
        bookBorrow.setUser(user);
        bookBorrow.setBook(bookname);
        Book book=new Book();
        bookBorrowRepository.save(bookBorrow);
        return "redirect:/allbooks";
    }


}
