package com.library;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.library.controllers.RegistrationController;
import com.library.model.User;
import com.library.repository.UserRepository;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
;

public class RegistrationControllerTest {

    /*
    @Test
    public void shouldShowRegistratrion() throws Exception
    {
        RegistrationController controller=new RegistrationController();
        MockMvc mockMvc=standaloneSetup(controller).build();
        mockMvc.perform(get("/library/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception{
        UserRepository mockRepository=mock(UserRepository.class);
        User unsaved=new User("kryniu","24hours","Krzysztof","Dragan","krzysztof.dragangm@gmail.com");
        User saved=new User(24L,"kryniu","24hours","Krzysztof","Dragan","krzysztof.draganfm@gmail.com");

        RegistrationController registrationController=new RegistrationController(mockRepository);
        MockMvc mockMvc=standaloneSetup(registrationController).build();

        mockMvc.perform(post("/spitter/register")
                .param("firstName", "Krzysztof")
                .param("lastName", "Dragan")
                .param("username", "kryniu")
                .param("password", "24hours")
                .param("email", "krzysztof.draganfm@gmail.com"))
                .andExpect(redirectedUrl("/library/kryniu"));

        verify(mockRepository, atLeastOnce()).save(unsaved);
    }
    */
}
