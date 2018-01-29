package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Kontroler zarządzający stroną domową
 */
@Controller
public class HomeController {

    /**
     * Zwraca widok na katalog startowy
     *
     * @return "index"
     */
    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    /**
     * Zwraca widok na katalog domowy
     *
     * @return "home"
     */
    @RequestMapping(value = "/home")
    public String home(){return "home";}
}
