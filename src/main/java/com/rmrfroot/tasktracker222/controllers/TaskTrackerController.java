package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Task;
import com.rmrfroot.tasktracker222.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-tracker-user")
public class  TaskTrackerController {

    private TaskService taskService;

    public TaskTrackerController(TaskService taskService) {
        super();
        this.taskService = taskService;
    }
    @GetMapping()
    public List<Task> getAllTask(){
        return taskService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        return new ResponseEntity<>(taskService.findById(id),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Task> saveTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Task> updateTask(@PathVariable("id") int id, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.update(task,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id) {
        taskService.deleteById(id);
        return new ResponseEntity<>("Task deleted.", HttpStatus.OK);
    }

}
