package com.rmrfroot.tasktracker222.services;

import com.rmrfroot.tasktracker222.entities.Drill;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

//drill dao service

public interface DrillDaoService {

    public List<Drill> findAll();

    public Drill findById(int id);

    public Drill save(Drill drill);

    public void deleteById(int id);

    public Drill update( int id,Drill drill);

    public List<Drill> findDrillsInWeekOfDateByID(String date, int id);

    public LocalDate findStartOfWeek(String date);

    public LocalDate findEndOfWeek(String date);

    public int[] findDatesForScheduleByDate(String date);

    public LocalDate convertStringToLocalDate(String date);

    public LocalDate getLocalDateOfDrill(Drill drill);

    public LocalDate convertDateToLocalDate(Date date);

    public String[] getDaysOfWeekWithConcurrency(LinkedHashMap<DayOfWeek, Integer> drills);

    public LinkedHashMap<DayOfWeek, Integer> getDrillConcurrencyMap(List<Drill> drills);

    public int findConcurrencyInDay(List<Drill> drills);

    public HashMap<DayOfWeek, List<Drill>> sortDrillsByDayOfWeek(List<Drill> drills);
}
