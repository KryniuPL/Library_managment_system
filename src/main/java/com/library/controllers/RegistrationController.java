package com.library.controllers;


import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/library")
public class RegistrationController {

    private UserRepository userRepository;

/*
    @RequestMapping(value = "/register",method = GET)
    public String showRegistrationForm(Model model)
    {
        model.addAttribute(new User());
        return "registerForm";
    }
*/
}
