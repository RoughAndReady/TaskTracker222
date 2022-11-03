package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Drill Collection Controller for Drill Collection
 * @author Brian
 */
@RestController
@RequestMapping("/drillCollection")
public class DrillCollectionController {

    @Autowired
    private DrillDaoService drillDaoService;

    public DrillCollectionController(DrillDaoService drillDaoService){
        super();
        this.drillDaoService = drillDaoService;
    }

    /**
     * @param model
     * @return collection of drills
     */
    @GetMapping
    public String main(Model model) {
        List<Drill> drillList = drillDaoService.findAll();

        model.addAttribute("drills", drillList);
        return "drillCollection";
    }

    /**
     * @param id for searching for drill
     * @return HTML response code 200
     */
    @GetMapping("/{id}")
    public ResponseEntity<Drill> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(drillDaoService.findById(id), HttpStatus.OK);
    }


    /**
     * @param drill to save drill to database
     * @return HTML response code 200
     */
    @PostMapping
    public ResponseEntity<Drill> save(@RequestBody Drill drill) {
        return new ResponseEntity<>(drillDaoService.save(drill), HttpStatus.CREATED);
    }


    /**
     * @param id to search for drill
     * @param drill for updating drill
     * @return HTML response code 200
     */
    @PutMapping("/{id}")
    public ResponseEntity<Drill> update(@PathVariable("id") int id, @RequestBody Drill drill) {
        return new ResponseEntity<>(drillDaoService.update(id,drill), HttpStatus.OK);
    }

    /**
     * @param id for deleting drill
     */
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        drillDaoService.deleteById(id);
    }
}
