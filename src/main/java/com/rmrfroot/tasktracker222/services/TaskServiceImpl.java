package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.dao.TaskDAO;
import com.rmrfroot.tasktracker222.entities.Task;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskDAO taskDAO;

    private TaskServiceImpl(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }
    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public Task findById(int theId) {
        return null;
    }

    @Override
    public void save(Task task) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
