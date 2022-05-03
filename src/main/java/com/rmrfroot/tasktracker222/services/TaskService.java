package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.Task;
import java.util.List;

public interface TaskService {
    List<Task> findAll();
    Task findById(int theId);

    Task save(Task task);

    void deleteById(int theId);

    Task update(Task task, int id);
}
