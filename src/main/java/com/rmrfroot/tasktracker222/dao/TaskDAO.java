package com.rmrfroot.tasktracker222.dao;

import com.rmrfroot.tasktracker222.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskDAO extends JpaRepository<Task, Integer> {
}
