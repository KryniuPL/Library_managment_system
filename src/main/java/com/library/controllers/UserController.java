package com.library.controllers;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "allusers";
    }

    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id){
        userRepository.delete(id);
        return "redirect:/allusers";
    }

    //Can go wrong with mapping
    @RequestMapping(value="/allusers/search", method = RequestMethod.POST)
    public String searchUsers(@Valid @ModelAttribute("phrase") String phrase, Model model){
        List<User> userList = userRepository.findPhrase(phrase);
        if(userList.size() == 0)
            return "redirect:/allusers";

        model.addAttribute("userList",userList);
        return "allusers";
    }

}
