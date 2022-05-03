package com.rmrfroot.tasktracker222.entities;

import java.util.ArrayList;
import java.util.List;

public class TaskCollection {

    private List<Task> taskCollection;

    public TaskCollection() {    //ArrayList
        taskCollection = new ArrayList<>();
    }

    public void add(Task task) {
        taskCollection.add(task);
    }

    //public void remove(Day day) {
    //}

    public int isEmpty() {
        if(taskCollection.size() == 0)
            return 0;
        return 1;
    }

    public int getSize() {
        return taskCollection.size();
    }

}
