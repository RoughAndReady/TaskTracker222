package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.dao.UsersDao;
import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.services.DrillScheduleService;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
//@SpringBootTest
class UserControllerTest {

    //@InjectMocks
    @MockBean
    private DrillScheduleService drillScheduleService;

    //@InjectMocks
    @MockBean
    private UsersDaoService usersDaoService;

    @Autowired
    private WebApplicationContext webApplicationContext;


    @MockBean
    private PoolClientInterface poolClientInterface;

    @MockBean
    UsersDao usersDao;

    @MockBean
    UserController userController;

    @Autowired
    MockMvc mockMvc;

    private java.util.ArrayList<String> teamList =new ArrayList<>(Arrays.asList("team1", "team2"));

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }



    @WithMockUser("spring")
    @Test
    void getUsersCollection() throws Exception {

        when(usersDaoService.findAll()).thenReturn(List.of(new User("brianfrey", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList)));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(status().isOk());
    }

    @Test
    void getUsersCollectionWithoutAuth() throws Exception {

        //test performed without spring security mock user

        when(usersDaoService.findAll()).thenReturn(List.of(new User("brianfrey", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList)));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(status().isFound());
    }

    @WithMockUser(username = "admin",roles={"USER","ADMIN"})
    @Test
    void userEditSubmit() throws Exception{

        int id = 1;
        User user = new User("brianfrey", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);
        user.setId(1);
        User updatedUser = new User("new", "Ben", "Frank",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);
        updatedUser.setId(2);


        given(usersDaoService.findById(1)).willReturn((user));

        //when(usersDaoService.findById(id)).thenReturn(Optional.ofNullable(user));
        //when(usersDaoService.save((User.class))).thenReturn(updatedUser);


    }

    @WithMockUser(username = "admin",roles={"USER","ADMIN"})
    @Test
    void userEditDelete() throws Exception {
        //userEditRequest.setId(String.valueOf(1));
        //this.mockMvc.perform(MockMvcRequestBuilders.post("/users")).andExpect(redirectedUrl("/users"));
        //this.mockMvc.perform(MockMvcRequestBuilders.post("/users")).andExpect(redirectedUrl("http://localhost:8080/login/oauth2/code/cognito"));
    }

    @Test
    void findById() {
    }

    @Test
    void accessControl() {
    }

    @Test
    void addUser() {
    }

    @Test
    void saveUser() {
    }

    @Test
    void updateUser() {


    }


    @Test
    @WithMockUser(username = "admin",roles={"USER","ADMIN"})
    void deleteUserById() throws Exception {

        int id = 1;
        User user = new User("brianfrey", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);
        user.setId(1);
        given(usersDaoService.findUsersById(id)).willReturn((user));
        doNothing().when(usersDaoService).deleteById(user.getId());

        this.mockMvc.perform(delete("/api/users/{id}", user.getId()))
                .andExpect(status().isOk());

                //.andExpect(jsonPath("$.email", is(user.getEmail())))

                //.andExpect(jsonPath("$.name", is(user.getName())));

    }
}