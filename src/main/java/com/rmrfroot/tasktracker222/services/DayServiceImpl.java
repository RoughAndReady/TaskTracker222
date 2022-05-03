package com.rmrfroot.tasktracker222.services;


import com.rmrfroot.tasktracker222.dao.DaysDAO;
import com.rmrfroot.tasktracker222.entities.Day;
import com.rmrfroot.tasktracker222.exception.ResourceNotFoundException;
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
        return daysDAO.findById(theId).orElseThrow(() -> new ResourceNotFoundException("Day", "Id", theId));
    }

    @Override
    public Day save(Day day) {
        return daysDAO.save(day);
    }

    @Override
    public void deleteById(int theId) {
        daysDAO.findById(theId).orElseThrow(RuntimeException::new);
        daysDAO.deleteById(theId);
    }

    @Override
    public Day update(Day day, int id) {
        Day existingDay = daysDAO.findById(id).orElseThrow(RuntimeException::new);
        existingDay.setFirstName(day.getFirstName());
        existingDay.setLastName(day.getLastName());
        existingDay.setPassword(day.getPassword());
        existingDay.setEmail(day.getEmail());
        daysDAO.save(existingDay);
        return existingDay;
    }

}
