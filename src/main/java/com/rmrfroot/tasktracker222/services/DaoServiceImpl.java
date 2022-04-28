package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.dao.DaysDAO;
import com.rmrfroot.tasktracker222.entities.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DaoServiceImpl implements DaoService {

    @Autowired
    private DaysDAO daysDAOs;


    @Override
    public List<Day> findAll() {
        return daysDAOs.findAll();
    }

    @Override
    public Day findById(int theId) {
        Optional<Day> result = daysDAOs.findById(theId);
        Day acc=null;
        if(result.isPresent()) {
            acc=result.get();
        }
        else {
            //we didn't find the employee
            throw new RuntimeException("Did not find account id - "+theId);
        }
        return acc;
    }

    @Override
    public void save(Day day) {
        daysDAOs.save(day);
    }

    @Override
    public void deleteById(int theId) {
        daysDAOs.deleteById(theId);
    }

}
