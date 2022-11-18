package com.rmrfroot.tasktracker222.services;


import com.rmrfroot.tasktracker222.dao.DrillDAO;
import com.rmrfroot.tasktracker222.entities.DrillSchedules;
import com.rmrfroot.tasktracker222.entities.Drill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmrfroot.tasktracker222.dao.CustomDrillSchedulesDAO;
import java.util.List;
import java.util.Optional;

/**
 * drill dao implementation
 * @author Brian Frey
 * @author Tobechi
 */
@Service
public class DrillDaoImpl implements DrillDaoService{

    @Autowired
    private DrillDAO drillDAO;

    @Autowired
    private CustomDrillSchedulesDAO customDrillSchedulesDAO;

    /**
     * Finds all drills that are registered in the system
     * @author Brian Frey
     * @return all drills
     */
    @Override
    public List<Drill> findAll() {
        return drillDAO.findAll();
    }

    /**
     * function to find the drills by ID
     * @param id
     * there is data validation
     * it prints if the ID number matches
     * @throws RuntimeException if the id doesn't match it prints an error "Drill not find drill id ###"
     * @author Brian Frey
     */
    @Override
    public Drill findById(int id) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill d;
        if(result.isPresent()) {
            d = result.get();
        }
        else {
            return null;
        }
        return d;
    }

    /**
     * function to save the drill
     * @author Brian Frey
     */
    @Override
    public Drill save(Drill drill) {
        drillDAO.save(drill);
        return drill;
    }

    /**
     * function to delete a drill by their id
     * @author Brian Frey
     * @param id
     * there is a data validation
     * if the id matches, then the drill will delete
     * @throws RuntimeException if the id doesn't match then it throws a exception
     */
    @Override
    public void deleteById(int id) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill d = null;
        if(result.isPresent()) {
            d = result.get();
            drillDAO.deleteById(id);
        }
        else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }


    }

    /**
     * function to update the drill
     * @author Brian Frey
     * @param id int and the updated drill
     * there is data validation
     * @return if id matches then it goes ahead and updates the drill
     * @throws RuntimeException if the id doesn't match then throws and exception
     */
    @Override
    public Drill update(int id, Drill drill) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill updatedDrill;
        if(result.isPresent()) {
            updatedDrill = result.get();
//            drillDAO.deleteById(id);
        }
        else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }
        updatedDrill.setTitle(drill.getTitle());
        updatedDrill.setColor(drill.getColor());
        updatedDrill.setDate(drill.getDate());
        updatedDrill.setStartTime(drill.getStartTime());
        updatedDrill.setEndTime(drill.getEndTime());
        updatedDrill.setLocation(drill.getLocation());
        updatedDrill.setOfficerName(drill.getOfficerName());
        updatedDrill.setDescription(drill.getDescription());
        drillDAO.save(updatedDrill);
        return updatedDrill;
    }

    /**
     * @author Tobechi
     * @param title true or false depending on if there is drill data or not
     */
    @Override
    public Boolean hasDrillData(String title) {
        return null;
    }

    /**
     *  constructor to register drill data to database
     * @author Tobechi
     */
    @Override
    public void registerDrillToDatabase(String name, String event_title, String start_date, String deadline_date, String location, String title, String admin_name, String officer_email, String created_timestamp, String note) {
        DrillSchedules drill = new DrillSchedules(event_title, start_date, deadline_date, location, admin_name, officer_email, note, created_timestamp);
        customDrillSchedulesDAO.save(drill);
    }


}
