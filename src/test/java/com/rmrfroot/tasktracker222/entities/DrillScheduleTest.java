//package com.rmrfroot.tasktracker222.entities;
//
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class DrillScheduleTest {
//
//    String event_title = "event";
//    String location = "location";
//    String officer_name = "Brian Frey";
//    String officer_email = "bf@root.edu";
//    String note = "noted";
//    LocalDate start_date =  LocalDate.of(2012, 6, 30);;
//    LocalDate deadline_date =  LocalDate.of(2023, 6, 30);;
//    int id = 123;
//
//    DrillSchedule drillSchedule= new DrillSchedule(id, officer_name,
//            officer_email, event_title, location,
//            note, start_date, deadline_date);
//
//
//    @Test
//    void getOfficer_name() {
//        assertEquals(officer_name,drillSchedule.getOfficer_name());
//    }
//
//    @Test
//    void getOfficer_email() {
//        assertEquals(officer_email, drillSchedule.getOfficer_email());
//    }
//
//    @Test
//    void getEvent_title() {
//        assertEquals(event_title, drillSchedule.getEvent_title());
//    }
//
//    @Test
//    void getNote() {
//        assertEquals(note,drillSchedule.getNote());
//    }
//
//    @Test
//    void getStart_date() {
//        assertEquals(start_date,drillSchedule.getStart_date());
//    }
//
//    @Test
//    void getDeadline_date() {
//        assertEquals(deadline_date, drillSchedule.getDeadline_date());
//    }
//}