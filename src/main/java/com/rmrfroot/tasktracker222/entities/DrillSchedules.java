package com.rmrfroot.tasktracker222.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a drill schedule
 * @author Visoth
 */
@Entity
@Table(name="drill_schedule")
public class DrillSchedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="event_title")
    private String event_title;
    @Column(name="start_date")
    private String start_date;
    @Column(name="deadline_date")
    private String deadline_date;
    @Column(name="location")
    private String location;
    @Column(name="admin_name")
    private String admin_name;
    @Column(name="officer_email")
    private String officer_email;
    @Column(name="note")
    private String note;
    @Column(name="created_timestamp")
    private String created_timestamp;

    @ManyToMany(
            fetch=FetchType.LAZY,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.DETACH,
                    CascadeType.REFRESH
            })
    @JoinTable(
            name="users_drill_schedule",
            joinColumns =@JoinColumn(name="drill_schedule_id"),
            inverseJoinColumns = @JoinColumn(name="users_id")
    )
    private List<User> userList;

    public DrillSchedules() {
    }

    /**
     * Creates drill schedule with all the specific detail
     * @param event_title drill title
     * @param start_date drill start date
     * @param deadline_date drill completion deadline
     * @param location drill location
     * @param admin_name person in charge of the drill
     * @param officer_email email of the person in charge
     * @param note drill detail
     * @param created_timestamp drill created timestamp
     */
    public DrillSchedules(String event_title, String start_date, String deadline_date, String location, String admin_name, String officer_email, String note, String created_timestamp) {
        this.event_title = event_title;
        this.start_date = start_date;
        this.deadline_date = deadline_date;
        this.location = location;
        this.admin_name = admin_name;
        this.officer_email = officer_email;
        this.note = note;
        this.created_timestamp = created_timestamp;
    }

    /**
     * Gets the drill id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the drill id
     * @param id int containing drill id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets drill schedule title
     * @return drill title
     */
    public String getEvent_title() {
        return event_title;
    }

    /**
     * Sets drill schedule title
     * @param event_title String containing the drill schedule title
     */
    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    /**
     * Gets drill schedule start date
     * @return drill schedule start date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * Sets drill schedule start date
     * @param start_date String containing the drill schedule start date
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * Gets drill schedule deadline
     * @return drill schedule deadline
     */
    public String getDeadline_date() {
        return deadline_date;
    }

    /**
     * Sets drill schedule deadline
     * @param deadline_date String containing the drill schedule deadline
     */
    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    /**
     * Gets drill schedule location
     * @return drill schedule location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets drill schedule location
     * @param location String containing the drill schedule location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the email of the office in charge of the drill
     * @return officer in charge's email
     */
    public String getOfficer_email() {
        return officer_email;
    }

    /**
     * Sets email for the officer in charge of the drill
     * @param officer_email String containing email of the officer in charge of the drill
     */
    public void setOfficer_email(String officer_email) {
        this.officer_email = officer_email;
    }

    /**
     * Gets drill's note
     * @return any note for the specific drill
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets drill schedule note
     * @param note String containing the drill schedule note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the name of the admin in charge
     * @return admin name
     */
    public String getAdmin_name() {
        return admin_name;
    }

    /**
     * Sets drill schedule admin name
     * @param admin_name String containing the drill schedule admin's name
     */
    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }
    /**
     * Gets the timestamp of drill creation
     * @return drill created timestamp
     */
    public String getCreated_timestamp() {
        return created_timestamp;
    }

    /**
     * Sets drill schedule creation time
     * @param created_timestamp String containing the time this drill schedule was created
     */
    public void setCreated_timestamp(String created_timestamp) {
        this.created_timestamp = created_timestamp;
    }

    /**
     * Gets the list of users
     * @return list of users
     */
    public List<User> getUsersList() {
        return userList;
    }

    /**
     * Sets drill schedule user list
     * @param userList List containing all the users that have a drill scheduled
     */
    public void setUsersList(List<User> userList) {
        this.userList = userList;
    }
    /**
     * Adds user to the user list
     * @param user adding user to the list of users
     */
    public void addUsers(User user){
        if(userList ==null){
            userList =new ArrayList<>();
        }
        userList.add(user);
    }

}
