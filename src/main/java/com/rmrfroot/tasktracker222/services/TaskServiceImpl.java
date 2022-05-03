package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.dao.TaskDAO;
import com.rmrfroot.tasktracker222.entities.Task;
import com.rmrfroot.tasktracker222.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private TaskDAO taskDAO;

    private TaskServiceImpl(TaskDAO taskDAO) {
        super();
        this.taskDAO = taskDAO;
    }
    @Override
    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    @Override
    public Task findById(int theId) {
        return taskDAO.findById(theId).orElseThrow(() -> new ResourceNotFoundException("Task", "Id", theId));
    }

    @Override
    public Task save(Task task) {
        return taskDAO.save(task);
    }

    @Override
    public void deleteById(int theId) {
        taskDAO.findById(theId).orElseThrow(RuntimeException::new);
        taskDAO.deleteById(theId);
    }

    @Override
    public Task update(Task task, int id) {
        Task existingTask = taskDAO.findById(id).orElseThrow(RuntimeException::new);
        existingTask.setTitle(task.getTitle());
        existingTask.setTask_priority(task.getTask_priority());
        existingTask.setDescription(task.getDescription());
        existingTask.setPdf_file(task.getPdf_file());
        existingTask.setStartDate(task.getStartDate());
        existingTask.setDeadlineDate(task.getDeadlineDate());
        existingTask.setNote(task.getNote());
        taskDAO.save(existingTask);
        return existingTask;
    }

}

