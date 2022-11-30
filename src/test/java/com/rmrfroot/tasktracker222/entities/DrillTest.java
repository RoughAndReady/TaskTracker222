//package com.rmrfroot.tasktracker222.entities;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class DrillTest {
//
//    //Jira issue number DT129
//    //Junit tests for Drill class
//    int id = 1234;
//    String title = "Captain";
//    Calendar calendar = Calendar.getInstance();
//    Date date = new Date(calendar.getTimeInMillis());
//    Date startDate = new Date(calendar.getTimeInMillis());
//    Date deadlineDate = new Date(calendar.getTimeInMillis());
//    String location = "Sacto";
//    String officerName = "Brian Frey";
//    String officerEmail = "bf@root.edu";
//    String note = "noted";
//    Drill testDrill = new Drill(title,startDate,deadlineDate,location,officerName,officerEmail,note);
//
//    @Test
//    void setId() {
//        testDrill.setId(id);
//        assertEquals(id,testDrill.getId());
//    }
//
//    @Test
//    void setTitle() {
//        testDrill.setTitle("Major");
//        assertEquals("Major",testDrill.getTitle());
//    }
//
//    @Test
//    void setStartDate() {
//        testDrill.setStartDate(startDate);
//        assertEquals(startDate,testDrill.getStartDate());
//    }
//
//    @Test
//    void setDeadlineDate() {
//        testDrill.setDeadlineDate(deadlineDate);
//        assertEquals(deadlineDate,testDrill.getDeadlineDate());
//    }
//
//    @Test
//    void setLocation() {
//        testDrill.setLocation(location);
//        assertEquals(location,testDrill.getLocation());
//    }
//
//    @Test
//    void setOfficerName() {
//        testDrill.setOfficerName(officerName);
//        assertEquals(officerName,testDrill.getOfficerName());
//    }
//
//    @Test
//    void setOfficerEmail() {
//        testDrill.setOfficerEmail(officerEmail);
//        assertEquals(officerEmail,testDrill.getOfficerEmail());
//    }
//
//    @Test
//    void setNote() {
//        testDrill.setNote(note);
//        assertEquals(note,testDrill.getNote());
//    }
//
//    @Test
//    void getId() {
//        testDrill.setId(2222);
//        assertEquals(2222,testDrill.getId());
//    }
//
//    @Test
//    void getTitle() {
//        testDrill.setTitle(title);
//        assertEquals(title,testDrill.getTitle());
//    }
//
//    @Test
//    void getStartDate() {
//        assertEquals(startDate,testDrill.getStartDate());
//    }
//
//    @Test
//    void getDeadlineDate() {
//        assertEquals(deadlineDate,testDrill.getDeadlineDate());
//    }
//
//    @Test
//    void getLocation() {
//        assertEquals(location,testDrill.getLocation());
//    }
//
//    @Test
//    void getOfficerName() {
//        assertEquals(officerName,testDrill.getOfficerName());
//    }
//
//    @Test
//    void getOfficerEmail() {
//        assertEquals(officerEmail,testDrill.getOfficerEmail());
//    }
//
//    @Test
//    void getNote() {
//        assertEquals(note,testDrill.getNote());
//    }
//}