package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.awsCognito.PoolClientInterface;
import com.rmrfroot.tasktracker222.entities.Group;
import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import com.rmrfroot.tasktracker222.services.UsersDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class DrillController {
    @Autowired
    private DrillDaoService drillDaoService;

    @Autowired
    private UsersDaoService usersDaoService;

    public DrillController(DrillDaoService drillDaoService) {
        super();
        this.drillDaoService = drillDaoService;
    }

    /**
     * For use by a singular recipient.
     * Only shows drills that are assigned to them.
     */
    @GetMapping("/drill-schedule-recipient")
    public String drillScheduleRecipient(Model model, Principal principal) {

        /*
            If user exists, add admin status to model.
            if user does not exist, redirect to new user registration.
         */
        try{
            model.addAttribute("isAdmin",
                    usersDaoService.findUserByUsername(principal.getName()).isAdmin());
        } catch (NullPointerException n) {
            return "redirect:/new-user-registration";
        }
        catch (Exception e){
            e.printStackTrace();
        }

        List<Drill> drillsToAdd = new ArrayList<>();
        List<Drill> drillsAll = drillDaoService.findAll();

        model.addAttribute("drills", drillsToAdd);
        return "DrillScheduler";
    }

    @GetMapping("/drill-schedule-manager")
    public String drillScheduleManager(Model model, Principal principal) {

        /*
            If user exists and is admin, proceed.
            If user exists and is not admin, redirect to drill schedule.
            if user does not exist, redirect to new user registration.
         */
        try{
            if(!usersDaoService.findUserByUsername(principal.getName()).isAdmin()){
                return "redirect:/drill-schedule-recipient";
            }
        } catch (NullPointerException n) {
            return "redirect:/new-user-registration";
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Drill drillEditRequest = new Drill();
        List<Drill> allDrills = drillDaoService.findAll();

        /*
            Sort drills by date + start time
            See compareTo() implementation in Drill to modify this behavior
         */
        try {
            Collections.sort(allDrills);
        } catch(Exception e){
            e.printStackTrace();
        }

        /*
            Add a placeholder drill that will be used to create a new drill if requested
         */
        Drill newDrillRequest = new Drill();
        newDrillRequest.setTitle("+ New Drill");
        newDrillRequest.setId(-2);
        allDrills.add(0, newDrillRequest);


        model.addAttribute("drill", drillEditRequest);
        model.addAttribute("drills", allDrills);

        model.addAttribute("users", usersDaoService.findAll());

        model.addAttribute("ranks", Group.getRanks());
        model.addAttribute("flights", Group.getFlights());
        model.addAttribute("workcenters", Group.getWorkcenters());
        model.addAttribute("teams", Group.getTeams());
        model.addAttribute("locations", Group.getLocations());

        return "CreateDrill";
    }

    @PostMapping(value = "/edit-drill", params = "submit")
    public String editDrill(@ModelAttribute("drill") Drill drill, @ModelAttribute("custom-location") String customLocation){

        if(customLocation != null && customLocation.length() > 0){
            drill.setLocation(customLocation);
        }

        if(drillDaoService.findById(drill.getId()) == null){
            drillDaoService.save(drill);
        }
        else {
            drillDaoService.update(drill.getId(), drill);
        }

        return "redirect:/drill-schedule-manager";
    }

    @PostMapping(value = "/edit-drill", params = "delete")
    public String deleteDrill(@ModelAttribute("drill") Drill request) {
        try{
            Drill drillToDelete = drillDaoService.findById(request.getId());
            drillDaoService.deleteById(drillToDelete.getId());
        }catch (Exception e){
            System.out.println("Could not delete drill");
            return "redirect:/error";
        }
        return "redirect:/drill-schedule-manager";
    }

}
