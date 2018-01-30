package com.library.controllers;

import com.library.config.LibrarySetupConfig;
import com.library.model.BookBorrow;
import com.library.model.User;
import com.library.repository.BillRepository;
import com.library.repository.BookBorrowRepository;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Kontroler zarządzający obsługą użytkowników
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private BillRepository billRepository;


    @Autowired
    private BookBorrowRepository bookBorrowRepository;


    @Autowired
    private BookRepository bookRepository;

    /**
     * Lista użytkowników
     *
     * @return "allusers"
     */
    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "allusers";
    }

    /**
     * Usuwanie użytkownika
     *
     * @return "redirect:/allusers" przekierowuje na listę użytkowników
     */
    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUser(@Valid @PathVariable("id") Long id){
        userRepository.delete(id);
        return "redirect:/allusers";
    }

    /**
     * Usuwanie użytkownika
     *
     * @return "redirect:/allusers" przekierowuje na stronę logowania
     */
    @RequestMapping(value = "/deletethisuser", method = RequestMethod.GET)
    public String deleteThisUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.findByUsername(authentication.getName()).getUserID();
        userRepository.delete(id);
        return "redirect:/login";
    }

    /**
     * Wyszukiwanie użytkowników
     *
     * @return "allusers" zwraca widok na listę użytkowników
     */
    //Can go wrong with mapping
    @RequestMapping(value="/userssearch", method = RequestMethod.POST)
    public ModelAndView searchUsers(@Valid @ModelAttribute("phrase") String phrase, ModelAndView model){
        model.setViewName("allusers");

        String warrning = "Nic nie znaleziono!";


        List<User> userList = userRepository.findPhrase(phrase);
        if(userList.size() == 0) {
            model.addObject("notFoundMessage",warrning);
            return model;
        }

        model.addObject("userList",userList);
        return model;
    }

    /**
     * Profil użytkownika
     *
     * @return "ruserprofile" zwraca widok na profil użytkownika
     */
    @RequestMapping(value = "/profile")
    public String userProfile(Model model)
    {
        Double calculatedFine;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User username=userService.findByUsername(authentication.getName());
        String username2=username.getUsername();
        List<String> userBooks=userBooks=userRepository.userBooks(username2);
        List<Double> calculatedFines = new ArrayList<>();

        for (String userBook : userBooks) {
            Date endDate = billRepository.endDate(username2, userBook);
            LocalDate now = LocalDate.now();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String input1 = now.toString();
            String input2 = endDate.toString();
            try {
                Date date1 = format.parse(input1);
                Date date2 = format.parse(input2);
                long difference = date2.getTime() - date1.getTime();
                long daysToCalculate = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
                if (difference < 0) {
                    daysToCalculate = Math.abs(daysToCalculate);
                    calculatedFine = daysToCalculate * LibrarySetupConfig.FEE;
                    calculatedFines.add(calculatedFine);
                } else
                {
                    calculatedFine=0.0;
                    calculatedFines.add(calculatedFine);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        double sum=calculatedFines.stream().mapToDouble(Double::doubleValue).sum();

        model.addAttribute("user",username);
        model.addAttribute("numberOfBorrowedBooks",userRepository.books(username2));
        model.addAttribute("calculatedFines",sum);

        return "userprofile";
    }

    /**
     * Edytowanie danych użytkownika
     *
     * @return "edituser" zwraca widok na edycję użytkownika
     */
    @RequestMapping("/edituser")
    public String editUser(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",userService.findByUsername(authentication.getName()));
        return "edituser";
    }

    /**
     * Zapisuje edytowanego użytkownika
     *
     * @return "redirect:/profile" przekierowuje na profil użytkownika
     */
    @RequestMapping(value = "/saveediteduser",method = RequestMethod.POST)
    public String saveEditedUser(@Valid @ModelAttribute("user") User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.update(user.getFirstname(),user.getSurname(),user.getUsername(),user.getPassword(),user.getEmail(),user.getUserID());
        return "redirect:/profile";
    }


    /**
     * Wyświetla wypożyczone ksiązki dla aktualnie zalogowanego użytkownika
     *
     * @return "showBorrowedBooksProfile"
     */
    @RequestMapping(value = "/showBorrowedBooksProfile")
    public String showBooksForProfile(Model model)
    {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User username=userService.findByUsername(authentication.getName());
        Long id=username.getUserID();

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

        return "showBorrowedBooksProfile";
    }



}
