package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.dao.CustomUsersDAO;
import com.rmrfroot.tasktracker222.dao.UsersDao;
import com.rmrfroot.tasktracker222.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class is a service layer class which handle all the requests that users want to CRUD the database,
 * for every request needs to pass through this class before get into the repository classes because
 * we need to make sure all the requests is safe to send onto the database.
 *
 * @author Visoth Cheam
 * @Note @Transactional is Hibernate configure class that allows the Hibernate to verify requests, and
 * make sure there are no problems happen during sending, if anything's happening then the Hibernate
 * will use something called Rollback which will bring back all the data that have been touched by the
 * sending.
 */
@Service
public class UsersDaoServiceImpl implements UsersDaoService {
    @Autowired
    private UsersDao usersDAO;
    @Autowired

    private EntityManager entityManager;

    private CustomUsersDAO customUsersDAO;

    @Override
    public List<User> findAll() {
        return usersDAO.findAll();
    }

    //find the user by ID from the database
    @Override
    public User findById(int theId) {
        Optional<User> result = usersDAO.findById(theId);

        User acc;

        if (result.isPresent()) {
            acc = result.get();
        } else {
            //we didn't find the employee
            throw new RuntimeException("Did not find account id - " + theId);
        }
        return acc;
    }

    //delete the user by ID from the database
    @Override
    public void deleteById(int theId) {
        usersDAO.deleteById(theId);
    }

    //update the user information by ID from the database
    @Override
    public User update(int id, User user) {
        try {
            Optional<User> result = usersDAO.findById(id);

            User updatedUser;
            if (result.isPresent()) {
                updatedUser = result.get();
            } else {
                throw new RuntimeException("Did not find user id - " + id);
            }

            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());

            if (!user.getMilitaryEmail().isBlank())
                updatedUser.setMilitaryEmail(user.getMilitaryEmail());

            if (!user.getCivilianEmail().isBlank())
                updatedUser.setCivilianEmail(user.getCivilianEmail());

            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setOfficeNumber(user.getOfficeNumber());
            updatedUser.setRank(user.getRank());
            updatedUser.setWorkCenter(user.getWorkCenter());
            updatedUser.setFlight(user.getFlight());
            updatedUser.setTeams(user.getTeams());
            updatedUser.setAdmin(user.isAdmin());

            // If updating this user would result in no admins remaining, force user to remain an admin
            if(!isAdminPresent()){
                System.out.println("Cannot remove admin attribute from user [" + user.getUserName() + "] - there " +
                        "must be at least one user with admin attribute in database.");
                updatedUser.setAdmin(true);
            }

            usersDAO.save(updatedUser);
            return updatedUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //save or update the user from the database
    @Override
    @Transactional
    public void save(User user) {
        usersDAO.save(user);
    }

    //check the user email in the database
    @Override
    @Transactional
    public Boolean hasUserData(String email) {
        Session cSession = entityManager.unwrap(Session.class);
        Query<User> query = cSession.createQuery("from User where email=:email", User.class);
        query.setParameter("email", email);
        Boolean check = false;
        List<User> list = query.getResultList();
        try {
            if (list.size() > 0) {
                check = true;
            }
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    //find the user by Username from the database
    @Override
    public User findUserByUsername(String username) {
        Session cSession = entityManager.unwrap(Session.class);
        Query<User> query = cSession.createQuery("from User where userName=:username", User.class);
        query.setParameter("username", username);

        User user;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    //find the user by Email from the database
    @Override
    @Transactional
    public User findUserByEmail(String email) {
        Session cSession = entityManager.unwrap(Session.class);
        Query<User> query = cSession.createQuery("from User where email=:email", User.class);
        query.setParameter("email", email);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    //find the user by ID with HSQL request from the database
    @Override
    @Transactional
    public User findUsersById(int id) {
        Session cSession = entityManager.unwrap(Session.class);
        Query<User> query = cSession.createQuery("from User where id=:id", User.class);
        query.setParameter("id", id);

        User user = null;
        try {
            user = query.getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        return user;
    }

    //register the user to the database
    @Override
    @Transactional
    public void registerUserToDatabase(String userName, String firstName, String lastName, String militaryEmail, String civilianEmail,
                                       String email, String phoneNumber, String officeNumber, String rank, String workCenter,
                                       String flight, ArrayList<String> teams) {
        User user = new User(userName, firstName, lastName, militaryEmail, civilianEmail, email,
                phoneNumber, officeNumber, rank, workCenter,
                flight, teams);

        if(!isAdminPresent()){
            System.out.println("No users have the admin attribute yet. User [" + userName + "] has been given " +
                            "admin attribute to assist with setup.");
            user.setAdmin(true);
            user.setApproved(true);
        }

        usersDAO.save(user);
    }

    @Override
    public boolean isAdminPresent(){
        for (User user : findAll()) {
            if(user.isAdmin())
                return true;
        }
        return false;
    }
}
