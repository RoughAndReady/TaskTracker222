package com.rmrfroot.tasktracker222.dao;

import com.rmrfroot.tasktracker222.entities.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class UsersDAOImpl implements CustomUsersDAO{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Boolean hasUserData(String email) {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where email=:email", User.class);
        query.setParameter("email",email);
        Boolean check=false;
        List<User> list=query.getResultList();
        try{
            if(list.size()>0) {
                check=true;
            }
        }catch (Exception e){
            check=false;
        }
        return check;
    }


    @Override
    public User findUserByEmail(String email) {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where email=:email", User.class);
        query.setParameter("email",email);

        User user=null;
        try{
            user=query.getSingleResult();
        }catch (Exception e){
            user=null;
        }
        return user;
    }
    @Override
    public User findUsersById(int id){
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where id=:id", User.class);
        query.setParameter("id",id);

        User user=null;
        try{
            user=query.getSingleResult();
        }catch (Exception e){
            user=null;
        }
        return user;
    }
    @Override
    public void save(User user) {
        Session cSession=entityManager.unwrap(Session.class);
        cSession.saveOrUpdate(user);
    }

    @Override
    public void update(User user) {
        Session cSession=entityManager.unwrap(Session.class);
        cSession.update(user);
    }

    @Override
    public User findUserByUsername(String username) {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where username=:username", User.class);
        query.setParameter("username",username);

        User user=null;
        try{
            user=query.getSingleResult();
        }catch (Exception e){
            user=null;
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("select User from User", User.class);

        List<User> users;
        try {
            users = query.getResultList();
        }
        catch (Exception e) {
            users = null;
        }
        return users;
    }

    @Override
    public List<User> findUsersByWorkCenter(String workCenter) {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where workCenter = :workCenter", User.class);
        query.setParameter("workCenter", workCenter);

        List<User> users;
        try {
            users = query.getResultList();
        }
        catch (Exception e) {
            users = null;
        }
        return users;
    }

    @Override
    public List<User> findUsersByFlight(String flight) {
        Session cSession=entityManager.unwrap(Session.class);
        Query<User> query=cSession.createQuery("from User where flight = :flight", User.class);
        query.setParameter("flight", flight);

        List<User> users;
        try {
            users = query.getResultList();
        }
        catch (Exception e) {
            users = null;
        }
        return users;
    }

    @Override
    public List<User> findUsersByTeam(String team) {
        Session cSession=entityManager.unwrap(Session.class);

        Query<User> query= cSession.createQuery("from User where cast(teams as string) like concat('%',:team,'%') ", User.class);
        query.setParameter("team", team);

        List<User> users;
        try {
            users = query.getResultList();
        }
        catch (Exception e) {
            users = null;
        }
        return users;
    }

}
