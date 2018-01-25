package com.library.controllers;


import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    //Sometimes mapping redirects to: /allbooks/allbooks/search
    @RequestMapping(value="/allbooks/search", method = RequestMethod.POST)
    public String searchBooks(@Valid @ModelAttribute("phrase") String phrase, Model model){
        List<Book> bookList = bookRepository.findPhrase(phrase);
        if(bookList.size() == 0)
            return "redirect:/allbooks";

        model.addAttribute("bookList",bookList);
        return "allbooks";
    }

    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id){
        bookRepository.delete(id);
        return "redirect:/allbooks";
    }

    @RequestMapping(value = "/updatebook/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long id, Model model){
        model.addAttribute("book",bookRepository.findOne(id));
        return "updatebookform";
    }

    @RequestMapping(value = "/updatebook/save", method = RequestMethod.POST)
    public String saveUpdateBook(@Valid @ModelAttribute("book") Book book, Model model){
        model.addAttribute("author",book.getAuthor());
        model.addAttribute("name",book.getName());
        model.addAttribute("price",book.getName());
        model.addAttribute("price",book.getStatus());
        bookRepository.update(book.getAuthor(),book.getName(),book.getPrice(),book.getBookID());
        return "redirect:/allbooks";
    }

    @RequestMapping(value = "/sortbooks/{id}",method = RequestMethod.GET)
    public String sortBooks(@PathVariable("id") Integer id, Model model){
        switch (id){
            case 1:
                model.addAttribute("bookList",bookRepository.orderByNameAsc());
                break;
            case 2:
                model.addAttribute("bookList",bookRepository.orderByAuthorAsc());
                break;
            case 3:
                model.addAttribute("bookList",bookRepository.orderByPriceAsc());
                break;
        }

        return "redirect:/allbooks";
    }
}
