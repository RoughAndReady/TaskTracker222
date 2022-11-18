/*  This is the Controller file; definitely a work in progress

    skeleton code is built from Dan Vega's video from 6:52
        and screenshot sent by Amrin (thanks Amrin)
*/

package com.rmrfroot.tasktracker222.controllers;

//import com.rmrfroot.tasktracker222.cognitoClasses.CreateUserPool;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.User;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    // missing: .getInstance();
    @Autowired
    private PoolClientInterface poolClientInterface;

    @Autowired
    private UsersDaoService usersDaoService;

    @GetMapping("/")
    public String home(Model model, Principal principal)
    {
        model.addAttribute("username", principal.getName());
        /*List<String> userInfoList=poolClientInterface.getUserInfo(principal.getName());
        String email=userInfoList.get(3);
        if(!usersDaoService.hasUserData(email)) {
            ArrayList<String> teams=new ArrayList<>();
            teams.add("team1");
            teams.add("team2");
            usersDaoService.registerUserToDatabase(
                    principal.getName(),
                    "visoth",
                    "cheam",
                    "military@email.com",
                    "civil@email.com",
                    email,
                    "234234",
                    "21342314",
                    "rank",
                    "workcenter",
                    "flight",
                    teams
            );
            System.out.println("New users just added to database: "+ principal.getName());
        }
        */
        return "redirect:/users/accessControl";
    }
}
