package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import com.rmrfroot.tasktracker222.validations.ValidateDrill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Drill Scheduler Controller
 * @author Amrin
 * @author Noel
 * @author Tobechi
 */
@Controller
public class DrillSchedulerController {

    //get day collection singleton

    // ONLY GetMapping works for now
    // because front end has no way of importing data into the database
    @Autowired
    private DrillDaoService drillDaoService;

    @Autowired
    private PoolClientInterface poolClientInterface;

    public DrillSchedulerController(DrillDaoService drillDaoService) {
        super();
        this.drillDaoService = drillDaoService;
    }

    /**
     * For use by a singular recipient.
     * Only shows drills that are assigned to them.
     * @param model
     * @return DrillScheduler
     */
    @GetMapping("/drill-schedule-recipient")
    public String drillScheduleRecipient(Model model) {
        List<Drill> drillsToAdd = new ArrayList<>();
        List<Drill> drillsAll = drillDaoService.findAll();

        String username = getUsername();
        for (Drill drill : drillsAll) {
            /*
                TODO: Add all drills that match user ID
            */
        }


        model.addAttribute("drills", drillsToAdd);
        return "DrillScheduler";
    }

    /**
     * For use by a schedule manager.
     * Shows all scheduled drills that are assigned to officer.
     * @param model for Officers
     * @return collection of drill schedules
     */
    @GetMapping("/drill-schedule-manager")
    public String drillScheduleManager(Model model) {
        List<Drill> drillsToAdd = new ArrayList<>();
        List<Drill> drillsAll = drillDaoService.findAll();

        String username = getUsername();
        for (Drill drill : drillsAll) {
            /*
                TODO: Update to use username field instead of officer name
            */
            if (drill.getOfficerName().equals(username)) {
                drillsToAdd.add(drill);
            }
        }

        model.addAttribute("drills", drillsToAdd);
        return "DrillScheduler";
    }

    /**
     * Get drill collection
     * @param model
     * @return drill collection
     */
    @GetMapping("/drill-schedule-recipient/drills")                       // made a drills.html to check if scheduler
    public String getDrillCollection(Model model) {                          // is able to retrieve database
        model.addAttribute("drills", drillDaoService.findAll());
        return "drills";
    }

    /**
     * Search for drill by id
     * @param id for searching for drill
     * @param model
     * @return drill
     */
    @GetMapping("/drill-schedule-recipient/drills/{id}")
    public String findDrillById(@PathVariable("id") int id, Model model) {
        model.addAttribute("drills", drillDaoService.findById(id));
        return "drills";
    }

    /**
     * Test drill creation. Will later be removed.
     * @param model
     * @return drill creation for user
     */
    @GetMapping("/drill-schedule-manager/createDrill")
    public String createTestDrill(Model model) {
        //Drill drill = new Drill(event_title, start_date, deadline_date, location, admin_name, officer_email, note, created_timestamp);
        Drill drill = new Drill();
        drill.setTitle("Test title");

        drill.setOfficerName("Test officer");
        drill.setDescription("Test description");

        ArrayList<String> locationList = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            locationList.add("Location " + i);
        }

        ArrayList<String> teamList = new ArrayList();
        for (int i = 1; i < 5; i++) {
            teamList.add("Team " + i);
        }

        model.addAttribute("drill", drill);
        model.addAttribute("editing", false);

        model.addAttribute("locations", locationList);
        model.addAttribute("teams", teamList);
        return "CreateDrill";
    }

    /**
     * Edits a drill
     * @param id
     * @param model
     * @return edited drill
     */
    @GetMapping("/drill-schedule-manager/editDrill/{drill-id}")
    public String editDrill(@PathVariable("drill-id") int id, Model model) {
        ArrayList<String> locationList = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            locationList.add("Location " + i);
        }

        Drill drill = drillDaoService.findById(id);

        model.addAttribute("drill", drill);
        model.addAttribute("editing", true);

        model.addAttribute("locations", locationList);
        return "CreateDrill";
    }


    /**
     * Creates a new drill and posts to database
     * @param drill
     * @return access to created drill
     */
    @PostMapping("/create-drill")
    public String createDrill(@ModelAttribute("drills") Drill drill) {
        //drillDaoService.save(drill);
        return "redirect:/drill-schedule-recipient/drills";
    }

    /**
     * Updates drill in database according to id
     * @param id for updating drill
     * @param drill to update
     * @return HTML response code 200
     */
    @PutMapping()
    public ResponseEntity<Drill> updateDrill(@PathVariable("id") int id, Drill drill) {

        return new ResponseEntity<>(drillDaoService.update(id, drill), HttpStatus.OK);
    }

    /**
     * Deletes drill according to id
     * @param id for drill deletion
     */
    @DeleteMapping()
    public void deleteDrillById(@PathVariable("id") int id) {
        drillDaoService.deleteById(id);
    }

    /**
     * Authenticates username
     * @return the username of the active session user
     */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }

        return null;
    }

    /**
     * Saves drill to database. Verifies drill matches correct form.
     * @param validateDrill
     * @param errors
     * @param model
     * @param principal
     * @return redirect to saved drill, error page, or registration form
     */
    @PostMapping("/register-drill")
    public String saveDrill(@Valid @ModelAttribute("drills") ValidateDrill validateDrill, BindingResult errors, Model model, Principal principal) {
        if(errors.hasErrors()){
            return "registration_form";
        }
        try {
            //List<String> drillInfoList = poolClientInterface.getDrillInfo(principal.getName());
            List<String> drillInfoList = new ArrayList<>();
            String title = drillInfoList.get(0);
            if (!drillDaoService.hasDrillData(title)) {
                drillDaoService.registerDrillToDatabase(
                        principal.getName(),
                        validateDrill.getEvent_title(),
                        validateDrill.getStart_date(),
                        validateDrill.getDeadline_date(),
                        validateDrill.getLocation(),
                        title,
                        validateDrill.getAdmin_name(),
                        validateDrill.getOfficer_email(),
                        validateDrill.getCreated_timestamp(),
                        validateDrill.getNote()
                );
                System.out.println("New drill just added to database: " + principal.getName());
            }else{
                return "redirect:/";
            }
        }catch (Exception e){
            System.out.println("Something went wrong");
            return "redirect:/error";
        }
        return "redirect:/drill";
    }

}
