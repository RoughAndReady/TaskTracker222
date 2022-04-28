package com.rmrfroot.tasktracker222.services;


import com.rmrfroot.tasktracker222.dao.DaysDAO;
import com.rmrfroot.tasktracker222.entities.Day;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService{

    private DaysDAO daysDAO;

    public DayServiceImpl(DaysDAO daysDAO) {
        this.daysDAO = daysDAO;
    }
    @Override
    public List<Day> findAll() {
        return daysDAO.findAll();
    }

    @Override
    public Day findById(int theId) {
        return null;
    }

    @Override
    public void save(Day day) {

    }

    @Override
    public void deleteById(int theId) {

    }
}
