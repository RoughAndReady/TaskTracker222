package com.rmrfroot.tasktracker222.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Drill object class
 * @author Noel
 * @author Brian Frey
 */
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

    @Column(name="color")
    private String color;

    @Type(type = "participants")
    @Column(name = "participants", columnDefinition = "text[]")
    private ArrayList<String> participants;

    public Drill(){

    }

    /**
     * Creates drill with all its detail
     * @param title drill title
     * @param date drill date
     * @param startTime drill start time
     * @param endTime drill end time
     * @param location drill location
     * @param officerName officer in charge of the drill
     * @param description drill description
     * @param participants list of required participants for the drill
     */
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

    /**
     * Gets drill id
     * @return drill id
     */
    public int getId() {
        return id;
    }

    /**
     *Sets drill id
     * @param id int containing drill id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets drill title
     * @param title string containing drill title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets drill title
     * @return drill title
     */
    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Get drill date
     * @return drill date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets drill date
     * @param date date of the drill
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Drill start time
     * @return the time drill will start
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * Sets the time when the drill starts
     * @param startTime date that consists of drill start time
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets the time the drill ends
     * @return time when the drill concludes
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * Sets the time the drill ends
     * @param endTime date consisting of the time the drill ends
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets the location of the drill
     * @return drill location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the drill location
     * @param location string consisting of the drill location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the name of the officer in charge
     * @return the name of the officer in charge
     */
    public String getOfficerName() {
        return officerName;
    }

    /**
     * Sets the name of the officer in charge
     * @param officerName string consisting of the officer in charge's name
     */
    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    /**
     * Gets the description of the drill
     * @return drill description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the drill
     * @param description string containing the description of the drill
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets arraylist of all the drill participants
     * @return list of all participants
     */
    public ArrayList<String> getParticipants() {
        return participants;
    }

    /**
     * Set an arraylist of all the participants
     * @param participants Arraylist containing the list of the all the participants per drill
     */
    public void setParticipants(ArrayList<String> participants) {
        this.participants = participants;
    }
}

