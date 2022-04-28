package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Day;
import com.rmrfroot.tasktracker222.services.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DayCollectionController {

    @Autowired
    private DayService dayService;

    @GetMapping("/main/dayCollection")
    public List<Day> getDayCollection(){
        List<Day> dayList = dayService.findAll();

        return dayList;
    }
}
