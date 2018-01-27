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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/allusers", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList",userList);
        return "allusers";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public String deleteUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.findByUsername(authentication.getName()).getUserID();
        userRepository.delete(id);
        return "redirect:/allusers";
    }

    @RequestMapping(value = "/deletethisuser", method = RequestMethod.GET)
    public String deleteThisUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Long id = userService.findByUsername(authentication.getName()).getUserID();
        userRepository.delete(id);
        return "redirect:/login";
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

    @RequestMapping(value = "/profile")
    public String userProfile(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",userService.findByUsername(authentication.getName()));

        return "userprofile";
    }

    @RequestMapping("/edituser")
    public String editUser(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",userService.findByUsername(authentication.getName()));
        return "edituser";
    }

    @RequestMapping(value = "/saveediteduser",method = RequestMethod.POST)
    public String saveEditedUser(@Valid @ModelAttribute("user") User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.update(user.getFirstname(),user.getSurname(),user.getUsername(),user.getPassword(),user.getEmail(),user.getUserID());
        return "redirect:/profile";
    }

}
