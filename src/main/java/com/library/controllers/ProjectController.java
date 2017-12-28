package com.library.controllers;


import com.library.model.User;
import com.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class ProjectController {

    private UserRepository userRepository;
/*
    @Autowired
        public ProjectController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
*/
    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model)
    {
        model.addAttribute(userRepository.findUsers(Long.MAX_VALUE,20));
        return "users";
    }
}
