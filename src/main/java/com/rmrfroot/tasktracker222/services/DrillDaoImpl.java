package com.rmrfroot.tasktracker222.services;


import com.rmrfroot.tasktracker222.dao.DrillDAO;
import com.rmrfroot.tasktracker222.entities.DrillSchedules;
import com.rmrfroot.tasktracker222.entities.Drill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmrfroot.tasktracker222.dao.CustomDrillSchedulesDAO;
import java.util.List;
import java.util.Optional;

//drill dao implementation
@Service
public class DrillDaoImpl implements DrillDaoService{

    @Autowired
    private DrillDAO drillDAO;

    @Autowired
    private CustomDrillSchedulesDAO customDrillSchedulesDAO;
    @Override
    public List<Drill> findAll() {
        return drillDAO.findAll();
    }

    @Override
    public Drill findById(int id) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill d;
        if(result.isPresent()) {
            d = result.get();
        }
        else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }
        return d;
    }


    @Override
    public Drill save(Drill drill) {
        drillDAO.save(drill);
        return drill;
    }

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

    @Override
    public Drill update(int id, Drill drill) {
        Optional<Drill> result = drillDAO.findById(id);
        Drill updatedDrill;
        if(result.isPresent()) {
            updatedDrill = result.get();
            drillDAO.deleteById(id);
        }
        else {
            //drill not found
            throw new RuntimeException("Did not find drill id - " + id);
        }
        updatedDrill.setTitle(drill.getTitle());
        updatedDrill.setDate(drill.getDate());
        updatedDrill.setStartTime(drill.getStartTime());
        updatedDrill.setEndTime(drill.getEndTime());
        updatedDrill.setLocation(drill.getLocation());
        updatedDrill.setOfficerName(drill.getOfficerName());
        updatedDrill.setDescription(drill.getDescription());
        drillDAO.save(updatedDrill);
        return updatedDrill;
    }

    @Override
    public Boolean hasDrillData(String title) {
        return null;
    }

    @Override
    public void registerDrillToDatabase(String name, String event_title, String start_date, String deadline_date, String location, String title, String admin_name, String officer_email, String created_timestamp, String note) {
        DrillSchedules drill = new DrillSchedules(event_title, start_date, deadline_date, location, admin_name, officer_email, note, created_timestamp);
        customDrillSchedulesDAO.save(drill);
    }


}
