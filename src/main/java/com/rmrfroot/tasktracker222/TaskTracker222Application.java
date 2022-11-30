package com.rmrfroot.tasktracker222;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class TaskTracker222Application {

    public static void main(String[] args) {
//        createConfigDirectory();
        SpringApplication.run(TaskTracker222Application.class, args);
    }

    private static void createConfigDirectory(){
        try {
            Files.createDirectories(Paths.get("./config"));
        }
        catch(Exception e){
            System.out.println("Could not create config directory");
            e.printStackTrace();
        }
    }

}
