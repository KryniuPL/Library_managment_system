package com.library.controllers;

import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String deleteUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.findByUsername(authentication.getName()).getUserID();
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
    @RequestMapping(value="/allusers/search", method = RequestMethod.POST)
    public String searchUsers(@Valid @ModelAttribute("phrase") String phrase, Model model){
        List<User> userList = userRepository.findPhrase(phrase);
        if(userList.size() == 0)
            return "redirect:/allusers";

        model.addAttribute("userList",userList);
        return "allusers";
    }

    /**
     * Profil użytkownika
     *
     * @return "ruserprofile" zwraca widok na profil użytkownika
     */
    @RequestMapping(value = "/profile")
    public String userProfile(Model model)
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User username=userService.findByUsername(authentication.getName());
        String username2=username.getUsername();
        model.addAttribute("user",username);
        model.addAttribute("numberOfBorrowedBooks",userRepository.books(username2));

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
}
