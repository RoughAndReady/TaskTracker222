package com.rmrfroot.tasktracker222.entities;

import com.rmrfroot.tasktracker222.testobjects.Day;

import java.util.ArrayList;
import java.util.List;

public class DayCollection {

    private List<Day> dayCollection;
    public DayCollection() {    //ArrayList
        dayCollection = new ArrayList<>();
    }

    public void add(Day day) {
        dayCollection.add(day);
    }

    //public void remove(Day day) {
    //needs Day getters to implement
    //}

    public int isEmpty() {
        if(dayCollection.size() == 0)
            return 0;
        return 1;
    }

    public int getSize() {
        return dayCollection.size();
    }

}
