package com.rmrfroot.tasktracker222.services;


import com.rmrfroot.tasktracker222.dao.DrillDAO;
import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * drill dao implementation
 *
 * @author Brian Frey
 * @author Tobechi
 */
@Service
public class DrillDaoImpl implements DrillDaoService {

    @Autowired
    private DrillDAO drillDAO;

    @Autowired
    private UsersDaoService usersDaoService;

    /**
     * Finds all drills that are registered in the system
     *
     * @return all drills
     * @author Brian Frey
     */
    @Override
    public List<Drill> findAll() {
        return drillDAO.findAll();
    }

    /**
     * function to find the drills by ID
     *
     * @param id there is data validation
     *           it prints if the ID number matches
     * @throws RuntimeException if the id doesn't match it prints an error "Drill not find drill id ###"
     * @author Brian Frey
     */
    @Override
    public Drill findById(int id) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill d;
        if (result.isPresent()) {
            d = result.get();
        } else {
            return null;
        }
        return d;
    }

    /**
     * function to save the drill
     *
     * @author Brian Frey
     */
    @Override
    public Drill save(Drill drill) {
        drillDAO.save(drill);
        return drill;
    }

    /**
     * function to delete a drill by their id
     *
     * @param id there is a data validation
     *           if the id matches, then the drill will delete
     * @throws RuntimeException if the id doesn't match then it throws a exception
     * @author Brian Frey
     */
    @Override
    public void deleteById(int id) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill d = null;
        if (result.isPresent()) {
            d = result.get();
            drillDAO.deleteById(id);
        } else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }


    }

    /**
     * function to update the drill
     *
     * @param id int and the updated drill
     *           there is data validation
     * @return if id matches then it goes ahead and updates the drill
     * @throws RuntimeException if the id doesn't match then throws and exception
     * @author Brian Frey
     */
    @Override
    public Drill update(int id, Drill drill) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill updatedDrill;
        if (result.isPresent()) {
            updatedDrill = result.get();
//            drillDAO.deleteById(id);
        } else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }
        updatedDrill.setTitle(drill.getTitle());
        updatedDrill.setColor(drill.getColor());
        updatedDrill.setDate(drill.getDate());
        updatedDrill.setStartTime(drill.getStartTime());
        updatedDrill.setEndTime(drill.getEndTime());
        updatedDrill.setLocation(drill.getLocation());
        updatedDrill.setReportToID(drill.getReportToID());
        updatedDrill.setParticipants(drill.getParticipants());
        updatedDrill.setDescription(drill.getDescription());
        drillDAO.save(updatedDrill);
        return updatedDrill;
    }

    public List<Drill> findDrillsInWeekOfDate(String date) {
        List<Drill> allDrills = drillDAO.findAll();
        List<Drill> out = new ArrayList<>();

        LocalDate startDate = findStartOfWeek(date);
        LocalDate endDate = findEndOfWeek(date);

        for (Drill drill : allDrills) {
            LocalDate drillDate = getLocalDateOfDrill(drill);

            if (drillDate.isBefore(endDate) && drillDate.isAfter(startDate)
                    || drillDate.isEqual(startDate) || drillDate.isEqual(endDate)) {
                out.add(drill);
            }
        }

        return out;
    }

    public List<Drill> findDrillsInWeekOfDateByID(String date, int id) {
        List<Drill> allDrills = findDrillsInWeekOfDate(date);
        List<Drill> out = new ArrayList<>();

        User user = usersDaoService.findUsersById(id);

        for (Drill drill : allDrills) {
            List<String> participants = drill.getParticipants();


            if (participants.contains(Integer.toString(user.getId()))) {
                out.add(drill);
            } else {
                List<String> commonGroups = new ArrayList<>(drill.getParticipants());

                List<String> userGroups = new ArrayList<>();
                userGroups.add(user.getRank());
                userGroups.add(user.getFlight());
                userGroups.add(user.getWorkCenter());
                userGroups.addAll(user.getTeams());

                commonGroups.retainAll(userGroups);

                if (!commonGroups.isEmpty()) {
                    out.add(drill);
                }
            }
        }

        return out;
    }

    public LocalDate findStartOfWeek(String date) {
        LocalDate originalDate = convertStringToLocalDate(date);
        LocalDate startDate = originalDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        return startDate;
    }

    public LocalDate findEndOfWeek(String date) {
        LocalDate originalDate = convertStringToLocalDate(date);
        LocalDate endDate = originalDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        return endDate;
    }

    public int[] findDatesForScheduleByDate(String date) {
        ArrayList<Integer> dates = new ArrayList<>();

        LocalDate startDate = findStartOfWeek(date);
        LocalDate endDate = findEndOfWeek(date);

        List<LocalDate> datesToAdd = startDate.datesUntil(endDate).collect(Collectors.toList());

        for(LocalDate d : datesToAdd) {
            dates.add(d.getDayOfMonth());
            System.out.println(d.getDayOfMonth());
        }
        dates.add(endDate.getDayOfMonth()); //LocalDate.datesUntil() is not inclusive, add final date

        return dates.stream().mapToInt(i->i).toArray();
    }

    public LocalDate convertStringToLocalDate(String date) {
        return LocalDate.of(Integer.parseInt(date.substring(0, 4)),
                Integer.parseInt(date.substring(5, 7)),
                Integer.parseInt(date.substring(8, 10)));
    }

    public LocalDate getLocalDateOfDrill(Drill drill) {
        return convertDateToLocalDate(drill.getDate());
    }

    public LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Date combineDateAndTime(Date date, Date timeToFix) {
        timeToFix.setYear(date.getYear());
        timeToFix.setMonth(date.getMonth());
        timeToFix.setDate(date.getDate());

        return timeToFix;
    }

    public String[] getDaysOfWeekWithConcurrency(LinkedHashMap<DayOfWeek, Integer> drills) {
        List<String> columns = new ArrayList<>();

        drills.forEach((k, v) -> {
            for (int i = 0; i <= v; i++) {
                columns.add(k.toString().substring(0, 1) + k.toString().substring(1, 3).toLowerCase()
                        + (v > 0 ? "-" + i : ""));
            }
        });

        return columns.toArray(new String[columns.size()]);
    }

    public LinkedHashMap<DayOfWeek, Integer> getDrillConcurrencyMap(List<Drill> drills) {
        LinkedHashMap<DayOfWeek, Integer> concurrency = new LinkedHashMap<>();
        LinkedHashMap<DayOfWeek, List<Drill>> drillsByDay = sortDrillsByDayOfWeek(drills);

        drillsByDay.forEach((k, v) -> {
            concurrency.put(k, findConcurrencyInDay(v));
        });

        return concurrency;
    }

    public int findConcurrencyInDay(List<Drill> drills) {
        int concurrency = 0;

        for (Drill drill : drills) {
            int drillConcurrency = 0;
            Date startTime = combineDateAndTime(drill.getDate(), drill.getStartTime());
            Date endTime = combineDateAndTime(drill.getDate(), drill.getEndTime());

            for (Drill drillToCompare : drills) {
                if (drill != drillToCompare) {
                    Date startTimeCompare = combineDateAndTime(drillToCompare.getDate(), drillToCompare.getStartTime());
                    Date endTimeCompare = combineDateAndTime(drillToCompare.getDate(), drillToCompare.getEndTime());

                    if ((startTime.before(endTimeCompare) || startTime.equals(endTimeCompare)) &&
                            (startTimeCompare.before(endTime) || startTimeCompare.equals(endTime))) {
                        drillConcurrency++;
                    }
                }
            }

            concurrency = Math.max(concurrency, drillConcurrency);
        }

        return concurrency;
    }

    public LinkedHashMap<DayOfWeek, List<Drill>> sortDrillsByDayOfWeek(List<Drill> drills) {
        LinkedHashMap<DayOfWeek, List<Drill>> drillsByDay = new LinkedHashMap<>();

        List<Drill> drillsSunday = new ArrayList<>();
        List<Drill> drillsMonday = new ArrayList<>();
        List<Drill> drillsTuesday = new ArrayList<>();
        List<Drill> drillsWednesday = new ArrayList<>();
        List<Drill> drillsThursday = new ArrayList<>();
        List<Drill> drillsFriday = new ArrayList<>();
        List<Drill> drillsSaturday = new ArrayList<>();

        for (Drill drill : drills) {
            LocalDate drillDate = getLocalDateOfDrill(drill);

            if (drillDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                drillsSunday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.MONDAY)
                drillsMonday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.TUESDAY)
                drillsTuesday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.WEDNESDAY)
                drillsWednesday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.THURSDAY)
                drillsThursday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.FRIDAY)
                drillsFriday.add(drill);
            else if (drillDate.getDayOfWeek() == DayOfWeek.SATURDAY)
                drillsSaturday.add(drill);
        }

        drillsByDay.put(DayOfWeek.SUNDAY, drillsSunday);
        drillsByDay.put(DayOfWeek.MONDAY, drillsMonday);
        drillsByDay.put(DayOfWeek.TUESDAY, drillsTuesday);
        drillsByDay.put(DayOfWeek.WEDNESDAY, drillsWednesday);
        drillsByDay.put(DayOfWeek.THURSDAY, drillsThursday);
        drillsByDay.put(DayOfWeek.FRIDAY, drillsFriday);
        drillsByDay.put(DayOfWeek.SATURDAY, drillsSaturday);

        return drillsByDay;
    }
}
