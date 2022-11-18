package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Generic Collection controller for User Collection
 * @author Tobechi
 * @author Noel
 */
@RestController
@RequestMapping("/userCollection")
public class UserCollectionController {

    @Autowired
    private UsersDaoService usersDaoService;

    public UserCollectionController(UsersDaoService usersDaoService) {
        super();
        this.usersDaoService = usersDaoService;
    }

    /**
     * @return Json collection of users in String form
     */
    @GetMapping
    public String main(Model model) {
        List<User> userList = usersDaoService.findAll();

        model.addAttribute("users", userList);
        return "userCollection";
    }

    /**
     * Search a User class by an integer id
     * @param id to be searched
     * @return HTML response code 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(usersDaoService.findById(id), HttpStatus.OK);
    }

    /**
     * Add a new user to the database
     * @param user to be added
     */
    @PostMapping()
    public void save(@RequestBody User user) {
        usersDaoService.save(user);
    }
    /**
     * Update user's attributes
     * @param id of user to be updated
     * @param user body to be updated
     * @return HTML response code 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody User user) {
        return new ResponseEntity<>(usersDaoService.update(id,user), HttpStatus.OK);
    }

    /**
     * Delete a user
     * @param id of user to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        usersDaoService.deleteById(id);
    }
}
