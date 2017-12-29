package com.library.controllers;


import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/library")
public class RegistrationController {

    private UserRepository userRepository;
/*
    @Autowired
    public RegistrationController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
*/


    /*
    @Autowired
    public RegistrationController(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    */
    @RequestMapping(value = "/register",method = GET)
    public String showRegistrationForm(Model model)
    {
        model.addAttribute(new User());
        return "registerForm";
    }

    @RequestMapping(value = "/register",method = POST)
    public String processRegistratrion(@Valid User user, Errors errors)
    {
        if(errors.hasErrors())
        {
            return "registerForm";
        }
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
