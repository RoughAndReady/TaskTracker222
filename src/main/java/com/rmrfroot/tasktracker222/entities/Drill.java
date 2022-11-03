package com.rmrfroot.tasktracker222.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

//Drill object class
@Entity
@Table(name="drills")
@TypeDef(
        name = "participants",
        typeClass = ListArrayType.class
)
public class Drill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="drill_name")
    private String title;

    @Column(name="date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name="start_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Date startTime;

    @Column(name="end_time")
    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    @Column(name="location")
    private String location;

    @Column(name="officer_name")
    private String officerName;

    @Column(name="description")
    private String description;

    @Type(type = "participants")
    @Column(name = "participants", columnDefinition = "text[]")
    private ArrayList<String> participants;

    public Drill(){

    }
    public Drill(String title, Date date, Date startTime, Date endTime, String location, String officerName,
                 String description, ArrayList<String> participants) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.officerName = officerName;
        this.description = description;
        this.participants = participants;
    }

    public Drill(String event_title, String start_date, String deadline_date, String location, String admin_name, String officer_email, String note, String created_timestamp) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}

