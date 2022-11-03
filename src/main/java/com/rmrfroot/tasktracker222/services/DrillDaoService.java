package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.Drill;

import java.util.List;

//drill dao service

public interface DrillDaoService {

    public List<Drill> findAll();

    public Drill findById(int id);

    public Drill save(Drill drill);

    public void deleteById(int id);

    public Drill update( int id,Drill drill);

    public Boolean hasDrillData(String title);

    public void registerDrillToDatabase(String name, String event_title, String start_date, String deadline_date,
                                 String location, String title, String admin_name, String officer_email, String created_timestamp, String note);


}
