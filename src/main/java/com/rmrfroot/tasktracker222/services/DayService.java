package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.Day;

import java.util.List;

public interface DayService {

    List<Day> findAll();

    Day findById(int theId);

    Day save(Day day);

    void deleteById(int theId);

    Day update(Day day, int id);
}
