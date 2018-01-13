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
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.FormView;
import javax.validation.Valid;
import java.util.ArrayList;
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
        model.addAttribute("price",book.getName());
        model.addAttribute("price",book.getStatus());
        book.setStatus("free");
        bookRepository.save(book);
        return "redirect:/allbooks";
    }

    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList",bookList);
        return "allbooks";
    }

    //Doesn't work yet
    @RequestMapping(value="/allbooks/search", method = RequestMethod.POST)
    public String searchBooks(@Valid @ModelAttribute("phrase") String phrase, Model model){
        List<Book> bookList = bookRepository.findPhrase(phrase);
        if(bookList.size() == 0)
            return "redirect:/allbooks";

        model.addAttribute("bookList",bookList);
        return "allbooks";
    }

    @RequestMapping(value = "/deletebook", method = RequestMethod.POST)
    public String deleteBook(@RequestParam("bookID") long bookID){
        bookRepository.delete(bookID);
        return "redirect:/allbooks";
    }
}
