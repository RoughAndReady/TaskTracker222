package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.Task;
import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(int theId);

    void save(Task task);

    void deleteById(int theId);
}
