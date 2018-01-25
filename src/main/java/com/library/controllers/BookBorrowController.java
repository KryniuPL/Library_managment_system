package com.library.controllers;

import com.library.model.BookBorrow;
import com.library.repository.BookBorrowRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class BookBorrowController{

    @Autowired
    private BookBorrowRepository bookBorrowRepository;

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/bookborrow/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long id, Model model){
        BookBorrow bookBorrow = new BookBorrow();
        bookBorrow.setBook(bookRepository.findOne(id));
        model.addAttribute("bookShow",bookBorrow.getBook());
        model.addAttribute("bookBorrow",bookBorrow);
        return "bookborrowForm";
    }

    @RequestMapping(value = "/bookborrow/save", method = RequestMethod.POST)
    public String saveBorrowBook(@Valid @ModelAttribute("bookborrow") BookBorrow bookBorrow,Model model)
    {

        bookBorrow.setStartDate(new Date());
        model.addAttribute("endDate",bookBorrow.getEndDate());
        model.addAttribute("bookID",bookBorrow.getBook());
        model.addAttribute("userID",bookBorrow.getEndDate());
        bookBorrowRepository.save(bookBorrow);
        return "redirect:/allbooks";
    }


}
