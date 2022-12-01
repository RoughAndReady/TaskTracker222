package com.rmrfroot.tasktracker222.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DrillTest {

    String title = "title";
    Date date = new Date();
    java.util.Date startTime = new Date();
    Date endTime = new Date();
    String location = "location";
    Integer reportToID = 1;
    String description = "description";
    int id = 111;


    private java.util.ArrayList<String> participants =new ArrayList<>(Arrays.asList("team1", "team2"));

    private Drill drill = new Drill(title, date, startTime, endTime, location, reportToID, description, participants);



    @Test
    void compareTo() {
        Drill drill1 = new Drill(title, date, startTime, endTime, location, reportToID, description, participants);
        assertEquals(drill1.compareTo(drill),drill.compareTo(drill1) );
    }

    @Test
    void getId() {
        drill.setId(id);
        assertEquals(id,drill.getId());
    }

    @Test
    void setId() {
        drill.setId(3);
        assertEquals(3,drill.getId());
    }

    @Test
    void setTitle() {
        drill.setTitle("other title");
        assertEquals("other title",drill.getTitle());
    }

    @Test
    void getTitle() {
        drill.setTitle("and another title");
        assertEquals("and another title",drill.getTitle());
    }

    @Test
    void getColor() {
        drill.setColor("blue");
        assertEquals("blue",drill.getColor());
    }

    @Test
    void setColor() {
        drill.setColor("red");
        assertEquals("red",drill.getColor());
    }

    @Test
    void getDate() {
        assertEquals(date,drill.getDate());
    }

    @Test
    void setDate() {
        Date date1 = new Date();
        drill.setDate(date1);
        assertEquals(date1,drill.getDate());
    }

    @Test
    void getStartTime() {
        assertEquals(startTime,drill.getStartTime());
    }

    @Test
    void setStartTime() {
        java.util.Date startTimeNew = new Date();
        drill.setStartTime(startTimeNew);
        assertEquals(startTimeNew,drill.getStartTime());
    }

    @Test
    void getEndTime() {
        assertEquals(endTime,drill.getEndTime());
    }

    @Test
    void setEndTime() {
        java.util.Date endTimeNew = new Date();
        drill.setEndTime(endTimeNew);
        assertEquals(endTimeNew,drill.getEndTime());
    }

    @Test
    void getLocation() {
        assertEquals(location,drill.getLocation());
    }

    @Test
    void setLocation() {
        drill.setLocation("new location");
        assertEquals("new location",drill.getLocation());
    }

    @Test
    void getReportToID() {
        assertEquals(reportToID,drill.getReportToID());
    }

    @Test
    void setReportToID() {
        drill.setReportToID(385);
        assertEquals(385,drill.getReportToID());
    }

    @Test
    void getDescription() {
        assertEquals(description,drill.getDescription());
    }

    @Test
    void setDescription() {
        drill.setDescription("new description");
        assertEquals("new description",drill.getDescription());
    }

    @Test
    void getParticipants() {
        assertEquals(participants,drill.getParticipants());
    }

    @Test
    void setParticipants() {
        java.util.ArrayList<String> participantsNew =new ArrayList<>(Arrays.asList("new1", "new2"));
        drill.setParticipants(participantsNew);
        assertEquals(participantsNew,drill.getParticipants());
    }
}