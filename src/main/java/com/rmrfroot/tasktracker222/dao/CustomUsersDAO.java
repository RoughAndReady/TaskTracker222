package com.rmrfroot.tasktracker222.dao;

import com.rmrfroot.tasktracker222.entities.User;

import java.util.List;

public interface CustomUsersDAO {

    public Boolean hasUserData(String email);
    public User findUserByEmail(String email);
    public User findUsersById(int id);
    public void save(User user);
    public void update(User user);
    public User findUserByUsername(String username);

    public List<User> findAll();

    public List<User> findUsersByWorkCenter(String workCenter);
    public List<User> findUsersByFlight(String flight);
    public List<User> findUsersByTeam(String team);
}
