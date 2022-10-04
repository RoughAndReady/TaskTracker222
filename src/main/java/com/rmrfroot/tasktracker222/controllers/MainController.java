/*  This is the Controller file; definitely a work in progress

    skeleton code is built from Dan Vega's video from 6:52
        and screenshot sent by Amrin (thanks Amrin)
*/

package com.rmrfroot.tasktracker222.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {
    // missing: .getInstance();

    @GetMapping("/")
    public String home(Model model, Principal principal)
    {
        model.addAttribute("username", principal.getName());

        return "main";
    }
}
