package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.User;

import java.util.ArrayList;
import java.util.List;

public interface UsersDaoService {

    public List<User> findAll();

    public User findById(int theId);

    public void save(User user);

    public void deleteById(int theId);

    User update(int id, User day);

    public Boolean hasUserData(String email);
    public User findUserByUsername(String username);

    public User findUserByEmail(String email);
    public User findUsersById(int id);
    public void registerUserToDatabase(String userName, String firstName, String lastName, String militaryEmail, String civilianEmail,
                                       String email,String phoneNumber, String officeNumber, String rank, String workCenter,
                                       String flight, ArrayList<String> teams);

    public boolean isAdminPresent();
}
