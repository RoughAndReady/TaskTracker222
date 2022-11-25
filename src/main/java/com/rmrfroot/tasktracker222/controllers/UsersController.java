package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.DrillSchedules;
import com.rmrfroot.tasktracker222.entities.Group;
import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.services.DrillScheduleService;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import com.rmrfroot.tasktracker222.validations.ValidatePassword;
import com.rmrfroot.tasktracker222.validations.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;
import java.util.List;
import java.util.*;

/**
 * Controller class for User
 * @author Visoth
 * @author Noel
 */
@Controller
public class UsersController {

    @Autowired
    private UsersDaoService usersDaoService;

    @Autowired
    private DrillScheduleService drillScheduleService;

    @Autowired
    private PoolClientInterface poolClientInterface;

    public UsersController(UsersDaoService usersDaoService) {
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

            usersDaoService.deleteById(userToDelete.getId());
            poolClientInterface.deleteUserByUsername(userToDelete.getUserName());
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

        List<String> userInfoList = poolClientInterface.getUserInfo(principal.getName());
        String email = userInfoList.get(3);
        if (usersDaoService.hasUserData(email)) {
            return "redirect:/";
        }
        return "registration_form";

    }

    /**
     * Add a user to the database
     * @param validateUser confirms user is a new user
     * @param errors Errors for exception
     * @param model view controller
     * @param principal user's credentials
     * @return to access control
     */
    @PostMapping("/register")
        public String saveUser(@Valid @ModelAttribute("users")ValidateUser validateUser, BindingResult errors, Model model, Principal principal) {
        /*if(errors.hasErrors()){
            return "registration_form";
        }*/
        try {
            List<String> userInfoList = poolClientInterface.getUserInfo(principal.getName());
            String email = userInfoList.get(3);
            if (!usersDaoService.hasUserData(email)) {
                /*ArrayList<String> teams = new ArrayList<>();
                teams.add("team1");
                teams.add("team2");*/
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
                System.out.println("New users just added to database: " + principal.getName());
            }else{
                return "redirect:/users/accessControl";
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
            return "redirect:/error";
        }
            return "redirect:/users/accessControl";
    }


    @GetMapping("/users/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDaoService.findById(id));
        return "users";
    }

    /**
     * Test controller to see Users collected
     * @param model view controller
     * @param workCenter sorts Users by
     * @return user list
     */

    @GetMapping("users/workcenter/{workcenter}")
    public String getUsersByWorkCenter(Model model,@PathVariable("workcenter") String workCenter) {
        model.addAttribute("user",usersDaoService.findUsersByWorkCenter(workCenter));
        return "users";
    }

    /**
     * Test controller to see Users collected
     * @param model view controller
     * @param flight sorts Users by
     * @return user list
     */
    @GetMapping("users/flight/{flight}")
    public String getUsersByFlight(Model model,@PathVariable("flight") String flight) {
        model.addAttribute("user",usersDaoService.findUsersByFlight(flight));
        return "users";
    }

    /**
     * Test controller to see Users collected
     * @param model view controller
     * @param team sorts Users by
     * @return user list
     */
    @GetMapping("users/team/{team}")
    public String getUsersByTeam(Model model,@PathVariable("team") String team) {
        model.addAttribute("user",usersDaoService.findUsersByTeam(team));
        return "users";
    }

    /**
     * this postmapping have not test on the function testing
        @author Visoth Cheam
        @return No template has been created for a change password controller
     */
    @PostMapping("/users/changePassword")
    public String changePassword(@Valid @ModelAttribute("Password") ValidatePassword validatePassword, BindingResult errors,
                                 Principal principal,@RegisteredOAuth2AuthorizedClient("cognito") OAuth2AuthorizedClient authorizedClient
                                 ) {
        if (errors.hasErrors()) {
            return "updatePassword_form";
        }else if(!validatePassword.getNewPassword().equals(validatePassword.getOldPassword())){
            return "updatePassword_form";
        }
        try{
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            String accessTokenValue= accessToken.getTokenValue();
            poolClientInterface.updatePassword(
                    validatePassword.getOldPassword(),
                    validatePassword.getNewPassword(),
                    accessTokenValue,
                    principal.getName()
            );
        }catch (Exception e){
            System.out.println("Something went wrong");
            return "redirect:/error";
        }

        return "redirect:/users";
    }
}
