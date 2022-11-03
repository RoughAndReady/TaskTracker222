package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.DrillSchedules;
import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.entities.UserEditRequest;
import com.rmrfroot.tasktracker222.services.DrillScheduleService;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import com.rmrfroot.tasktracker222.validations.ValidateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;
import java.util.List;

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
    @GetMapping("/users")
    public String getUsersCollection(Model model) {
        List<User> allUsers = usersDaoService.findAll();
        List<User> usersToAdd = new ArrayList<>();
        User userEditRequest = new User();

        for (User u : allUsers) {
            //System.out.println(u.getEmail());
                usersToAdd.add(u);
        }

        model.addAttribute("users", allUsers);
        //model.addAttribute("usersToAdd", usersToAdd);
        model.addAttribute("userEditRequest", userEditRequest);
        return "UserManagement";
    }

    /**
     * Updates selected user's attributes
     * @param request User class to be changed
     * @return to the UserManagement site
     */
    @PostMapping(value = "/users", params = "submit")
    public String userEditSubmit(@ModelAttribute("userEditRequest") User request) {
        try {
            User u = usersDaoService.findById(request.getId());

            u.setFirstName(request.getFirstName());
            u.setLastName(request.getLastName());
            u.setMilitaryEmail(request.getMilitaryEmail());
            u.setCivilianEmail(request.getCivilianEmail());
            u.setPhoneNumber(request.getPhoneNumber());
            u.setOfficeNumber(request.getOfficeNumber());
            u.setRank(request.getRank());
            u.setWorkCenter(request.getWorkCenter());
            u.setFlight(request.getFlight());
            u.setTeams(request.getTeams());   //TODO - Integrate ArrayList-style team list

            usersDaoService.update(u.getId(), u);

            return "redirect:/users";
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
    @PostMapping(value = "/users", params = "delete")
    public String userEditDelete(@ModelAttribute("userEditRequest") User request) {
        System.out.println("delete");
        User u = usersDaoService.findById(request.getId());

        usersDaoService.deleteById(u.getId());
        //TODO - Add functionality to delete user from database and cognito

        return "redirect:/users";
    }


    @GetMapping("/users/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", usersDaoService.findById(id));
        return "users";
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
            DrillSchedules drillSchedules=drillScheduleService.findDrillSchedulesById(59);
            /*if(!user.findDrillScheduleById(59)){
                System.out.println("Something went wrong ");
                return "redirect:/error";
            }else */
                if(user.getEmail().equals(drillSchedules.getOfficer_email())) {
                System.out.println("manager-page");
                return "redirect:/drill-schedule-manager";
            } else
                System.out.println("recipient-page");
            return "redirect:/drill-schedule-recipient";
        }catch (Exception e){
            System.out.println("Something went wrong ");
            return "redirect:/error";
        }
    }

    /**
     * Adds a user to the system
     * @param model view controller
     * @param principal user's credentials
     * @return a registration form to collect user's information
     */
    @GetMapping("/users/newUser")
    public String addUser(Model model,Principal principal) {
        User user = new User();
        model.addAttribute("newUser", user);
        DrillSchedules drillSchedules1=new DrillSchedules(
               "lorem" ,
                "start_date",
                "deadline_date",
                "location",
                "admin_name",
                "officer_email",
                "note",
                "create_timestamp"
        );
        DrillSchedules drillSchedules2=new DrillSchedules(
                "lorem" ,
                "start_date",
                "deadline_date",
                "location",
                "admin_name",
                "officer_email",
                "note",
                "create_timestamp"
        );
        ArrayList<String> team =new ArrayList<>();
        team.add("team1");
        team.add("team2");
        User addUser1=new User(
                "visoth99",
                "visoth",
                "cheam",
                "visoth99@gmail.com",
                "visothMili@gmail.com",
                "visothMili@gmail.com",
                "912431234892349",
                "23432",
                "Lorem",
                "Clafornia branch",
                "f22",
                team
                );
        User addUser2=new User(
                "visoth99",
                "visoth",
                "cheam",
                "visoth99@gmail.com",
                "visothMili@gmail.com",
                "visothMili@gmail.com",
                "912431234892349",
                "23432",
                "Lorem",
                "Clafornia branch",
                "f22",
                team
        );
        User addUserAdmin=new User(
                "adderUserAdmin",
                "visoth",
                "cheam",
                "visoth99@gmail.com",
                "visothMili@gmail.com",
                "visothMili@gmail.com",
                "912431234892349",
                "23432",
                "Lorem",
                "Clafornia branch",
                "f22",
                team
        );
        /*drillScheduleService.save(drillSchedules1);
        drillSchedules1.addUsers(addUser1);
        drillSchedules1.addUsers(addUser2);
        usersDaoService.save(addUser1);
        usersDaoService.save(addUser2);

        //create new users and add to exist drill_schedules
        DrillSchedules drillSchedules=drillScheduleService.findDrillSchedulesById(53);
        drillSchedules.addUsers(addUser1);
        drillSchedules.addUsers(addUser2);
        usersDaoService.save(addUser1);
        usersDaoService.save(addUser2);

        //exist users with exist drill_schedules
        DrillSchedules drillSchedules=drillScheduleService.findDrillSchedulesById(54);
        Users usersExist=usersDaoService.findById(77);
        drillSchedules.addUsers(usersExist);
        usersDaoService.save(usersExist);

        //a new drill_schedule with exists an admin user, and recipient users
        Users usersExist=usersDaoService.findById(77);
        drillSchedules1.addUsers(usersDaoService.findUsersById(78));
        drillSchedules1.addUsers(usersDaoService.findUsersById(79));
        usersExist.addDrillSchedule(drillSchedules1);
        usersDaoService.save(usersExist);*/
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
        if(errors.hasErrors()){
            return "registration_form";
        }
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
     * Generic update User from database
     * @param id of User to be updated
     * @param user attributes to replace old data
     * @return HTML response code of 200
     */
    @PutMapping("users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, User user) {

        return new ResponseEntity<>(usersDaoService.update(id, user), HttpStatus.OK);
    }

    /**
     * Generic delete a user from database
     * @param id of user to be deleted
     */
    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable("id") int id) {
        usersDaoService.deleteById(id);
    }
}
