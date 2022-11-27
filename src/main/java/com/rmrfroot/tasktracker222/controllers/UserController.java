package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.Group;
import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import com.rmrfroot.tasktracker222.validations.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import javax.validation.Valid;
import java.util.List;
import java.util.*;

/**
 * Controller class for User
 * @author Visoth
 * @author Noel
 */
@Controller
public class UserController {

    @Autowired
    private UsersDaoService usersDaoService;

    @Autowired
    private PoolClientInterface poolClientInterface;

    public UserController(UsersDaoService usersDaoService) {
        super();
        this.usersDaoService = usersDaoService;
    }

    /**
     * Main Page for User Management
     * shows the list of the users in the system
     * and allows admin to change an user's attribute
     * @param model for the model view controller
     * @return front-end HTML
     */
    @GetMapping("/user-manager")
    public String getUsersCollection(Model model, Principal principal) {

        /*
            If user exists and is admin, proceed.
            If user exists and is not admin, redirect to drill schedule.
            if user does not exist, redirect to new user registration.
         */
        try{
            if(!usersDaoService.findUserByUsername(principal.getName()).isAdmin()){
                return "redirect:/drill-schedule-recipient";
            }
        } catch (NullPointerException n) {
            return "redirect:/new-user-registration";
        }
        catch (Exception e){
            e.printStackTrace();
        }

        List<User> allUsers = usersDaoService.findAll();
        User userEditRequest = new User();

        try {
            Collections.sort(allUsers);
        } catch (Exception e){
            System.out.println("Could not sort user list.");
            e.printStackTrace();
        }

        model.addAttribute("users", allUsers);
        model.addAttribute("userEditRequest", userEditRequest);

        model.addAttribute("ranks", Group.getRanks());
        model.addAttribute("flights", Group.getFlights());
        model.addAttribute("workcenters", Group.getWorkcenters());
        model.addAttribute("teams", Group.getTeams());

        return "UserManagement";
    }

    /**
     * Updates selected user's attributes
     * @param request User class to be changed
     * @return to the UserManagement site
     */
    @PostMapping(value = "/user-manager", params = "submit")
    public String userEditSubmit(@ModelAttribute("userEditRequest") User request) {
        try {
            User u = usersDaoService.findById(request.getId());

            // Translate blank values to null since POST does not allow null values
            if(request.getRank().equals(""))
                request.setRank(null);
            if(request.getFlight().equals(""))
                request.setFlight(null);
            if(request.getWorkCenter().equals(""))
                request.setWorkCenter(null);
            if(request.getTeams().isEmpty())
                request.setTeams(null);


            u.setFirstName(request.getFirstName());
            u.setLastName(request.getLastName());
            u.setMilitaryEmail(request.getMilitaryEmail());
            u.setCivilianEmail(request.getCivilianEmail());
            u.setPhoneNumber(request.getPhoneNumber());
            u.setOfficeNumber(request.getOfficeNumber());
            u.setRank(request.getRank());
            u.setWorkCenter(request.getWorkCenter());
            u.setFlight(request.getFlight());
            u.setTeams(request.getTeams());
            u.setAdmin(request.isAdmin());

            usersDaoService.update(u.getId(), u);

            return "redirect:/user-manager";
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete a user in the system
     * @param request User to be deleted
     * @return to UserManagement site
     */
    @PostMapping(value = "/user-manager", params = "delete")
    public String userEditDelete(@ModelAttribute("userEditRequest") User request, Principal principal) {
        try{
            User userToDelete = usersDaoService.findUsersById(request.getId());

            poolClientInterface.deleteUserByUsername(userToDelete.getUserName());
            usersDaoService.deleteById(userToDelete.getId());
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/user-manager";
    }

    /**
     * Determines the User's role
     * and redirects to a page depending on the role
     * @param model view controller
     * @param principal user's credentials
     * @return a page determined from the user's role
     */
    @GetMapping("users/accessControl")
    public String accessControl(Model model,Principal principal) {
        try {
            User user=usersDaoService.findUserByUsername(principal.getName());
            model.addAttribute("user", user);
            if(user.isAdmin()) {
                System.out.println("manager-page");
                return "redirect:/drill-schedule-manager";
            }
            else{
               System.out.println("recipient-page");
                return "redirect:/drill-schedule-recipient";
            }
        }
        catch (Exception e){
            System.out.println("No user found");
            return "redirect:/users/newUser";
        }
    }

    /**
     * Adds a user to the system
     * @param model view controller
     * @param principal user's credentials
     * @return a registration form to collect user's information
     */
    @GetMapping("/new-user-registration")
    public String addUser(Model model,Principal principal) {
        User user = new User();
        model.addAttribute("newUser", user);

        model.addAttribute("ranks", Group.getRanks());
        model.addAttribute("flights", Group.getFlights());
        model.addAttribute("workcenters", Group.getWorkcenters());
        model.addAttribute("teamList", Group.getTeams());

        List<String> userInfoList = poolClientInterface.getUserInfo(principal.getName());
        String email = userInfoList.get(3);
        if (usersDaoService.hasUserData(email)) {
            return "redirect:/";
        }

        return "RegistrationForm";
    }

    /**
     * Add a user to the database
     * @param validateUser confirms user is a new user
     * @param errors Errors for exception
     * @param model view controller
     * @param principal user's credentials
     * @return to access control
     */
    @PostMapping("/register-new-user")
        public String saveUser(@Valid @ModelAttribute("users")ValidateUser validateUser, BindingResult errors, Model model, Principal principal) {

        try {
            List<String> userInfoList = poolClientInterface.getUserInfo(principal.getName());
            String email = userInfoList.get(3);
            if (!usersDaoService.hasUserData(email)) {
                usersDaoService.registerUserToDatabase(
                        principal.getName(),
                        validateUser.getFirstName(),
                        validateUser.getLastName(),
                        validateUser.getMilitaryEmail(),
                        validateUser.getCivilianEmail(),
                        email,
                        validateUser.getPhoneNumber(),
                        validateUser.getOfficeNumber(),
                        validateUser.getRank(),
                        validateUser.getWorkCenter(),
                        validateUser.getFlight(),
                        validateUser.getTeams()
                );
                System.out.println("New user just added to database: " + principal.getName());
            }else{
                return "redirect:/";
            }
        }catch (Exception e){
            System.out.println("Could not register user: " + principal.getName());
            e.printStackTrace();
            return "redirect:/error";
        }
            return "redirect:/";
    }
}
