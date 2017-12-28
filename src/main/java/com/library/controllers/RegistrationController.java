package com.library.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/library")
public class RegistrationController {

    @RequestMapping(value = "/register",method = GET)
    public String showRegistrationForm()
    {
        return "registerForm";
    }
}
