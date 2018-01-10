package com.library.controllers;


import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String showAddBookForm() {
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(Book book){
        bookRepository.save(book);
        return "addbook";
    }

    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
