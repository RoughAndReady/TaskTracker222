package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Day;
import com.rmrfroot.tasktracker222.services.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Day Collection Controller Class for Day Collection
 * @author Brian
 * @author Visoth
 */
@RestController
@RequestMapping("/dayCollection")
public class DayCollectionController {


    @Autowired
    private DaoService daoService;


    /**
     * Gets a colection of days and creates a model
     * @param model
     * @return dayCollection
     */
    @GetMapping
    public String main(Model model){
        List<Day> dayList =daoService.findAll();

        model.addAttribute("days", dayList);
        return "dayCollection";
    }

    /**
     * Search a Day object by an integer id
     * @param id of day to be searched
     * @return ResponseEntity with HTML response code 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<Day> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(daoService.findById(id), HttpStatus.OK);
    }

    /**
     * Save a Day object to the database
     * @param day to be saved
     * @return ResponseEntity with HTML response code 200
     */
    @PostMapping
    public ResponseEntity<Day> save(@RequestBody Day day) {
        return new ResponseEntity<>(daoService.save(day), HttpStatus.CREATED);
    }

    /**
     * Update day's attributes
     * @param id of day to be updated
     * @param day body to be updated
     * @return ResponseEntity with HTML response code 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<Day> update(@PathVariable("id") int id, @RequestBody Day day) {
        return new ResponseEntity<>(daoService.update(id, day), HttpStatus.OK);
    }

    /**
     * Deletes a day
     * @param id of day to be removed
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        daoService.deleteById(id);
    }

}
