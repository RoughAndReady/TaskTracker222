package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.Group;
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

    @GetMapping("/drill-schedule-recipient/drills")                       // made a drills.html to check if scheduler
    public String getDrillCollection(Model model) {                          // is able to retrieve database
        model.addAttribute("drills", drillDaoService.findAll());
        return "drills";
    }

    @GetMapping("/drill-schedule-recipient/drills/{id}")
    public String findDrillById(@PathVariable("id") int id, Model model) {
        model.addAttribute("drills", drillDaoService.findById(id));
        return "drills";
    }

    @GetMapping("/drill-schedule-manager/createDrill")
    public String createTestDrill(Model model) {
        Drill drill = new Drill();

        model.addAttribute("drill", drill);
        model.addAttribute("editing", false);

        model.addAttribute("ranks", Group.getRanks());
        model.addAttribute("flights", Group.getFlights());
        model.addAttribute("workcenters", Group.getWorkcenters());
        model.addAttribute("teams", Group.getTeams());
        model.addAttribute("locations", Group.getLocations());

        return "CreateDrill";
    }

    @GetMapping("/drill-schedule-manager/editDrill/{drill-id}")
    public String editDrill(@PathVariable("drill-id") int id, Model model) {
        Drill drill = drillDaoService.findById(id);

        model.addAttribute("drill", drill);
        model.addAttribute("editing", true);
        model.addAttribute("drill-id", id);

        model.addAttribute("ranks", Group.getRanks());
        model.addAttribute("flights", Group.getFlights());
        model.addAttribute("workcenters", Group.getWorkcenters());
        model.addAttribute("teams", Group.getTeams());
        model.addAttribute("locations", Group.getLocations());

        return "CreateDrill";
    }


    @PostMapping("/create-drill")
    public String createDrill(@ModelAttribute("drills") Drill drill, @ModelAttribute("custom-location") String customLocation){

        if(customLocation != null && customLocation.length() > 0){
            drill.setLocation(customLocation);
        }

        if(drillDaoService.findById(drill.getId()) == null){
            drillDaoService.save(drill);
        }
        else {
            drillDaoService.update(drill.getId(), drill);
        }

        return "redirect:/drill-schedule-manager/editDrill/" + drill.getId();
    }

    @PutMapping()
    public ResponseEntity<Drill> updateDrill(@PathVariable("id") int id, Drill drill) {

        return new ResponseEntity<>(drillDaoService.update(id, drill), HttpStatus.OK);
    }

    @DeleteMapping()
    public void deleteDrillById(@PathVariable("id") int id) {
        drillDaoService.deleteById(id);
    }

    /**
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
