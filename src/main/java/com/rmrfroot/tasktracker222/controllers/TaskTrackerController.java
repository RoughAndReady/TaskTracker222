package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Task;
import com.rmrfroot.tasktracker222.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskTrackerController {

    @Autowired
    private TaskService taskService;
    @GetMapping("/task-tracker-user")
    public List<Task> taskList(){
        List<Task> taskList = taskService.findAll();

        return taskList;
    }
}
