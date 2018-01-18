package com.library;

import com.library.controllers.BooksController;
import com.library.controllers.HomeController;
import com.library.controllers.RegistrationController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class MappingTests {

    @Test
    public void testHomePage() throws Exception{
        HomeController controller=new HomeController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    public void shouldShowRegistration() throws Exception
    {
        RegistrationController controller=new RegistrationController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/library/register")).andExpect(view().name("registerForm"));
    }

    @Test
    public void testLoginPage() throws Exception
    {
     HomeController loginController=new HomeController();
     MockMvc mockMvc=standaloneSetup(loginController).build();
     mockMvc.perform(get("/login")).andExpect(view().name("login"));
    }

    /*
     @Test
     public void testAddingBooksPage() throws Exception
     {
         BooksController booksController=new BooksController();
         MockMvc mockMvc=standaloneSetup(booksController).build();
         mockMvc.perform(get("/addbook")).andExpect(view().name("addbook"));
     }
    */

}