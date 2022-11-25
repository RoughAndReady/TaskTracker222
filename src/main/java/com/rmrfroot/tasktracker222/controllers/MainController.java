package com.rmrfroot.tasktracker222.controllers;

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

    @Autowired
    private PoolClientInterface poolClientInterface;

    @Autowired
    private UsersDaoService usersDaoService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        return "redirect:/drill-schedule-recipient";
    }
}
