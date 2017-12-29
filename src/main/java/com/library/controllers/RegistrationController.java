package com.library.controllers;


import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/library")
public class RegistrationController {

    private UserRepository userRepository;

    public RegistrationController()
    {

    }



    /*
    @Autowired
    public RegistrationController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    */
    @RequestMapping(value = "/register",method = GET)
    public String showRegistrationForm()
    {
        return "registerForm";
    }

    @RequestMapping(value = "/register",method = POST)
    public String processRegistratrion(User user)
    {
        userRepository.save(user);
        return "redirect:/user/"+user.getUsername();
    }

    @RequestMapping(value = "/{username}",method = GET)
    public String showUserProfile(@PathVariable String username, Model model)
    {
        User user=userRepository.findByUsername(username);
        model.addAttribute(user);
        return "profile";
    }
}
