package com.rmrfroot.tasktracker222.controllers;

import com.rmrfroot.tasktracker222.entities.Drill;
import com.rmrfroot.tasktracker222.services.DrillDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//drill collection controller

@RestController
@RequestMapping("/drillCollection")
public class DrillCollectionController {

    @Autowired
    private DrillDaoService drillDaoService;


    public DrillCollectionController(DrillDaoService drillDaoService){
        super();
        this.drillDaoService = drillDaoService;
    }

    @GetMapping
    public String main(Model model) {
        List<Drill> drillList = drillDaoService.findAll();

        model.addAttribute("drills", drillList);
        return "drillCollection";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drill> findById(@PathVariable("id") int id) {
        return new ResponseEntity<>(drillDaoService.findById(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Drill> save(@RequestBody Drill drill) {
        return new ResponseEntity<>(drillDaoService.save(drill), HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Drill> update(@PathVariable("id") int id, @RequestBody Drill drill) {
        return new ResponseEntity<>(drillDaoService.update(id,drill), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") int id) {
        drillDaoService.deleteById(id);
    }
}
