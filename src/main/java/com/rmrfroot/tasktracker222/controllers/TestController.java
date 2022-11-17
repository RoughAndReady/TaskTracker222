/*  This is the Controller file; definitely a work in progress

    skeleton code is built from Dan Vega's video from 6:52
        and screenshot sent by Amrin (thanks Amrin)
*/

package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.Officer;
import com.rmrfroot.tasktracker222.entities.Group;
import com.rmrfroot.tasktracker222.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;

//@RestController   //@RestController automatically combines the @GetMapping and @ResponseBody annotations.
                    //@ResponseBody returns text rather than a template.
@Controller
@CrossOrigin
public class TestController {
    // missing: .getInstance();

    @GetMapping("/testpage")
    public String testPage(Model model) {
        //User.readTeamsFromFile();
        System.out.println("Loading test page");
        String[] ranks = Group.getWorkcenters();

        for(int i = 0; i < ranks.length; i++){
            System.out.println(ranks[i]);
        }

        model.addAttribute("newDrill", new TestObject());

        return "CreateDrill";
    }
}

class TestObject  {
    public Date date;
    public Date startTime;
    public Date endTime;

    public String location;

    public ArrayList<Officer> officers;
    public ArrayList<User> participants;

    public String description;

    public TestObject(){};
}