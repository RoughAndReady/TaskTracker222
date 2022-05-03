package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Day;
import com.rmrfroot.tasktracker222.services.DayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dayCollection")
public class DayCollectionController {

    private DayService dayService;

    public DayCollectionController(DayService dayService) {
        super();
        this.dayService = dayService;
    }

    @GetMapping()
    public List<Day> getDayCollection(){
        return dayService.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Day> getTaskById(@PathVariable("id") int id) {
        return new ResponseEntity<>(dayService.findById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Day> saveTask(@RequestBody Day day) {
        return new ResponseEntity<>(dayService.save(day), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Day> updateTask(@PathVariable("id") int id, @RequestBody Day day) {
        return new ResponseEntity<>(dayService.update(day,id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") int id) {
        dayService.deleteById(id);
        return new ResponseEntity<>("Task deleted.", HttpStatus.OK);
    }
}
