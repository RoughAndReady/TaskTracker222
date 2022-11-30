package com.rmrfroot.tasktracker222.controllers;

import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class FileController {

    @PostConstruct
    public static void createConfigDirectory(){
        System.out.println("Attempting to create config folder...");
        try {
            Files.createDirectories(Paths.get("./config"));
            System.out.println("Created!");
        }
        catch(Exception e){
            System.out.println("Could not create config directory");
            e.printStackTrace();
        }
    }
}
