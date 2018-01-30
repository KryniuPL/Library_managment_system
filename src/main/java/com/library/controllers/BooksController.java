package com.library.controllers;

import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

/**
 * Kontroler obsługujący operacje na repozytorium książki
 */
@Controller
public class BooksController {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Zwraca widok na książki
     *
     * @return "addbook" zwraca widok na addbook
     */
    @RequestMapping(value = "/addbook", method = RequestMethod.GET)
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    /**
     * Dodawanie nowej książki
     *
     * @return "redirect:/allbooks" przekierowuje na widok allbooks
     */
    @RequestMapping(value = "/addbook", method = RequestMethod.POST)
    public String addBook(@Valid @ModelAttribute("book") Book book, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addbook";
        }
        Book bookToUpdate = bookRepository.getIdenticalLike(book.getName(),book.getAuthor(),book.getIsbn());
        if(bookToUpdate != null){
            Long quantity = bookRepository.getQuantity(book.getName(),book.getAuthor(),book.getIsbn());
            bookRepository.update(bookToUpdate.getAuthor(),bookToUpdate.getName(),quantity+1,bookToUpdate.getIsbn(),bookToUpdate.getBookID());
        }else {
            book.setQuantity(1L);
            book.setStatus("free");
            bookRepository.save(book);
        }
        return "redirect:/allbooks";
    }

    /**
     * Lista wszystkich książek
     *
     * @return "allbooks" zwraca widok na allbooks
     */
    @RequestMapping(value = "/allbooks", method = RequestMethod.GET)
    public String getAllBooks(Model model) {
        List<Book> bookList = bookRepository.findAll();
        model.addAttribute("bookList",bookList);
        return "allbooks";
    }

    /**
     * Wyszukiwanie książek
     *
     * return model zwraca obiekt MAV
     */
    @RequestMapping(value="/bookssearch", method = RequestMethod.POST)
    public ModelAndView searchBooks(@Valid @ModelAttribute("phrase") String phrase, ModelAndView model){

        model.setViewName("allbooks");

        String warrning = "Nic nie znaleziono!";

        List<Book> bookList = bookRepository.findPhrase(phrase);
        if(bookList.size() == 0) {
            model.addObject("notFoundMessage",warrning);
            return model;
        }

        model.addObject("bookList",bookList);
        return model;
    }

    /**
     * Usuwanie książki
     *
     * @return "redirect:/allbooks" przekierowuje na widok allbooks
     */
    @RequestMapping(value = "/deletebook/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id){
        bookRepository.delete(id);
        return "redirect:/allbooks";
    }

    /**
     * Aktualizowowanie danych książki
     *
     * @return "updatebookform" zwraca widok na updatebookform
     */
    @RequestMapping(value = "/updatebook/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long id, Model model){
        model.addAttribute("book",bookRepository.findOne(id));
        return "updatebookform";
    }

    /**
     * Zapisuje aktualizowaną książkę
     *
     * @return "redirect:/allbooks" przekierowuje na widok allbooks
     */
    @RequestMapping(value = "/updatebook/save", method = RequestMethod.POST)
    public String saveUpdateBook(@Valid @ModelAttribute("book") Book book, Model model){
        model.addAttribute("author",book.getAuthor());
        model.addAttribute("name",book.getName());
        model.addAttribute("quantity",book.getQuantity());
        model.addAttribute("isbn",book.getIsbn());
        bookRepository.update(book.getAuthor(),book.getName(),book.getQuantity(),book.getIsbn(),book.getBookID());
        return "redirect:/allbooks";
    }

    /**
     * Sortuje książki
     *
     * @return "redirect:/allbooks" przekierowuje na widok allbooks
     */
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
