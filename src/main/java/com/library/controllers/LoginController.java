package com.library.controllers;

import com.library.model.Role;
import com.library.model.User;
import com.library.repository.RoleRepository;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.persistence.NamedQuery;
import javax.validation.Valid;

import java.sql.SQLException;
import java.sql.Statement;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registerForm");
        return modelAndView;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult)
    {
        ModelAndView modelAndView=new ModelAndView();
        User userExists=userService.findByUsername(user.getUsername());
        if(userExists!=null)
        {
            bindingResult.rejectValue("username","error.username","There is already a user registered with the username provided");
        }
        if(bindingResult.hasErrors())
        {
            modelAndView.setViewName("registerForm");
        }
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage","User has been registered successfully");
            modelAndView.addObject("user",new User());
            modelAndView.setViewName("registerForm");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView home()
    {
        ModelAndView modelAndView=new ModelAndView();
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findByUsername(authentication.getName());

            modelAndView.addObject("username", "Welcome" + " " + user.getFirstname() + "" + user.getSurname() + " (" + user.getEmail() + ")");
            modelAndView.setViewName("/home");
            return modelAndView;
    }

}
