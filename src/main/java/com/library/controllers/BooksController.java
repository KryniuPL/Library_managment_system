package com.library.controllers;


import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") Book book, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addbook";
        }
        model.addAttribute("author",book.getAuthor());
        model.addAttribute("name",book.getName());
        bookRepository.save(book);
        return "allbooks";
    }

    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public @ResponseBody
    List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
