package com.library;

import com.library.controllers.BooksController;
import com.library.controllers.HomeController;
import com.library.controllers.LoginController;
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
        LoginController controller=new LoginController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/register")).andExpect(view().name("registerForm"));
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
