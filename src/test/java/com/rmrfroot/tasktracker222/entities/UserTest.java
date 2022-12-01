package com.rmrfroot.tasktracker222.entities;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {

    private int id = 1234;
    private String email = "bfrey@root.edu";
    private String fname = "Brian";
    private String lname = "Frey";
    private String reg_date = "11/11/22";
    private String up_date = "2/2/23";
    private boolean admin = true;
    private String rank = "Captain";
    private String workCenter = "workCenter";
    private String flight = "flight";
    private java.util.ArrayList<String> teamList =new ArrayList<>(Arrays.asList("team1", "team2"));

    private User user = new User("brianfrey", "Brian", "Frey",
                 "root@gov.com","email@root.edu","email@root.edu",
                 "9165555555","1234", "Captain",
                 "workCenter","flight",teamList);

    @Test
    void compareTo() {
        User user1 = new User("brianfrey", "Brian", "Frey",
                "root@gov.com","email@root.edu","email@root.edu",
                "9165555555","1234", "Captain",
                "workCenter","flight",teamList);

        assertEquals(user.compareTo(user1),user1.compareTo(user) );

    }

    @Test
    void getId() {
        user.setId(id);
        assertEquals(id,user.getId());
    }

    @Test
    void setId() {
        user.setId(id);
        assertEquals(id,user.getId());
    }

    @Test
    void isValidEmailAddrRegex() {
    }

    @Test
    void getCivilianEmail() {
        assertEquals("email@root.edu",user.getCivilianEmail());
    }

    @Test
    void setCivilianEmail() {
        String newEmail = new String("newuser@csus.edu");
        user.setCivilianEmail(newEmail);
        assertEquals(newEmail,user.getCivilianEmail());
    }

    @Test
    void getMilitaryEmail() {
        assertEquals("root@gov.com",user.getMilitaryEmail());
    }

    @Test
    void setMilitaryEmail() {
        String newEmail = new String("newuser@csus.edu");
        user.setMilitaryEmail(newEmail);
        assertEquals(newEmail,user.getMilitaryEmail());
    }

    @Test
    void getFirstName() {
        assertEquals(fname,user.getFirstName());
    }

    @Test
    void setFirstName() {
        user.setFirstName("Bill");
        assertEquals("Bill",user.getFirstName());
    }

    @Test
    void getLastName() {
        assertEquals(lname,user.getLastName());
    }

    @Test
    void setLastName() {
        user.setLastName("Lee");
        assertEquals("Lee",user.getLastName());
    }

    @Test
    void getRank() {
        assertEquals(rank,user.getRank());
    }

    @Test
    void setRank() {
        user.setRank("Major");
        assertEquals("Major",user.getRank());
    }

    @Test
    void getWorkCenter() {
        assertEquals(workCenter,user.getWorkCenter());
    }

    @Test
    void setWorkCenter() {
        user.setWorkCenter("Other Center");
        assertEquals("Other Center",user.getWorkCenter());
    }

    @Test
    void getFlight() {
        assertEquals(flight,user.getFlight());
    }

    @Test
    void setFlight() {
        user.setFlight("Other Flight");
        assertEquals("Other Flight",user.getFlight());
    }

    @Test
    void addTeam() {
    }

    @Test
    void readGroup() {
    }

    @Test
    void readTeamsFromFile() {
    }

    @Test
    void getTeams() {
        assertEquals(teamList,user.getTeams());
    }

    @Test
    void setTeams() {
        java.util.ArrayList<String> newTeamList =new ArrayList<>(Arrays.asList("New_team1", "New_team2"));
        user.setTeams(newTeamList);
        assertEquals(newTeamList,user.getTeams());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("9165555555",user.getPhoneNumber());
    }

    @Test
    void setPhoneNumber() {
        String newNumber = "9163254657";
        user.setPhoneNumber(newNumber);
        assertEquals(newNumber,user.getPhoneNumber());
    }

    @Test
    void getOfficeNumber() {
        assertEquals("1234",user.getOfficeNumber());
    }

    @Test
    void setOfficeNumber() {
        user.setOfficeNumber("4321");
        assertEquals("4321",user.getOfficeNumber());
    }

    @Test
    void getUserName() {
        assertEquals("brianfrey",user.getUserName());
    }

    @Test
    void setUserName() {
        user.setUserName("billiejoe");
        assertEquals("billiejoe",user.getUserName());
    }

    @Test
    void getEmail() {
        assertEquals("email@root.edu",user.getEmail());
    }

    @Test
    void setEmail() {
        user.setEmail(email);
        assertEquals(email,user.getEmail());
    }

    @Test
    void setAdmin() {
        boolean t = true;
        user.setAdmin(t);
        assertEquals(true,user.isAdmin());
    }

    @Test
    void isAdmin() {
        user.setAdmin(false);
        assertEquals(false,user.isAdmin());
    }
}