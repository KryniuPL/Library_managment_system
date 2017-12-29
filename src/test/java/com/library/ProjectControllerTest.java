package com.library;

import com.library.model.User;
import com.library.repository.UserRepository;
import org.junit.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;
import static org.junit.matchers.JUnitMatchers.hasItems;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class ProjectControllerTest {

    public ProjectControllerTest(UserRepository mockRepository) {
    }
    /*
    @Test
    public void shouldShowRecentSpittles() throws Exception{
        List<User> expectedUsers=createUserList(20);
        UserRepository mockRepository=mock(UserRepository.class);

        when(mockRepository.findUsers(Long.MAX_VALUE,20)).thenReturn(expectedUsers);

        ProjectControllerTest controller=new ProjectControllerTest(mockRepository);
        MockMvc mockMvc=standaloneSetup(controller).setSingleView(new InternalResourceView("/WEB-INF/views/users.jsp")).build();

        mockMvc.perform(get("/users"))
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("usersList"))
                .andExpect(model().attribute("userList",hasItems(expectedUsers.toArray())));

    }

    private List<User> createUserList(int count)
    {
        List<User> users=new ArrayList<User>();
        for(int i=0;i<count;i++)
        {
            users.add(new User("User "+i,new Date()));
        }
        return users;
    }
    */
}
