package com.rmrfroot.tasktracker222.entities;

/*
import com.rmrfroot.tasktracker222.dao.CustomUsersDAO;
import com.rmrfroot.tasktracker222.dao.UsersDAOImpl;
import com.rmrfroot.tasktracker222.services.UsersDaoServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserCollectionControllerTests {

    //@Autowired
    @InjectMocks
    private UsersDaoServiceImpl usersDaoService;

    @Mock
    UsersDAOImpl usersDAO;

    @Mock
    CustomUsersDAO customUsersDAO;

    private java.util.ArrayList<String> teamList =new ArrayList<>(Arrays.asList("team1", "team2"));

    //@Autowired
    private User user1 = new User("brianfrey", "Brian", "Frey",
            "root@gov.com","email@root.edu","email@root.edu",
            "9165555555","1234", "Captain",
            "workCenter","flight",teamList);


    @Test
    public void saveUserTest() throws Exception{

        usersDaoService.save(user1);
        verify(usersDAO,times(1)).save(user1);
    }

    @Test
    public void hasUserDataTest() throws Exception{

        usersDAO.save(user1);
        when(usersDaoService.hasUserData("email@root.edu")).thenReturn(Boolean.TRUE);

    }

    @Test
    public void findUsersByIdTest() throws Exception{

        user1.setId(111);
        usersDAO.save(user1);
        when(usersDaoService.findUsersById(111)).thenReturn(user1);
        assertEquals(user1,usersDaoService.findUsersById(111));
    }

    @Test
    public void findUserByUsernameTest() throws Exception{

        usersDAO.save(user1);
        when(usersDaoService.findUserByUsername("brianfrey")).thenReturn(user1);
        assertEquals(user1,usersDaoService.findUserByUsername("brianfrey"));
    }

    @Test
    public void registerUserToDatabaseTest() throws Exception{

        User user2 = new User("billy", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);



        usersDaoService.registerUserToDatabase("billy", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);

       // user2 = usersDaoService.findUserByUsername("billy");
        String name = customUsersDAO.findUserByUsername("billy").getUserName();
        //assertEquals("billy",user2.getUserName());
        assertEquals("billy",name);


    }




}
*/