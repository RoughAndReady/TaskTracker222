package com.rmrfroot.tasktracker222.entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "register_date")
    private LocalDate register_date;

    @Column(name = "update_date")
    private LocalDate update_date;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "rank")
    private String rank;

    @Column(name = "workCenter")
    private String workCenter;

    @Column(name = "flight")
    private String flight;

    @ElementCollection
    @Column(name = "teams")
    private ArrayList<String> teamList = new ArrayList<String>();

    public User() {

    }

    public User(String email, String first_name, String last_name,
                LocalDate register_date, LocalDate update_date,
                boolean admin, String rank, String workCenter,
                String flight, ArrayList<String> teamList) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.register_date = register_date;
        this.update_date = update_date;
        this.admin = admin;
        this.rank = rank;
        this.workCenter = workCenter;
        this.flight = flight;
        this.teamList = teamList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.indexOf('@')<0 || email.indexOf('.')<0) {
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Not a valid email");
        }

    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        if(first_name.matches("[a-zA-Z]+")) {
            this.first_name = first_name;
        }
        else {
            throw new IllegalArgumentException("Not a valid name. First name should only contain letters");
        }
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        if(last_name.matches("[a-zA-Z]+")) {
            this.last_name = last_name;
        }
        else {
            throw new IllegalArgumentException("Not a valid name. Last name should only contain letters");
        }
    }

    public LocalDate getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDate register_date) {
//        still working to fix this to work properly
//        String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
//        if (register_date.matches(datePattern)) {
//            this.register_date = register_date;
//        }
//        else {
//            throw new IllegalArgumentException("Register date must be MM/DD/YYYY. Not a valid date.");
//        }
        this.register_date = register_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
//        still working to fix this to work properly
//        String datePattern = "\\d{1,2}-\\d{1,2}-\\d{4}";
//        if (update_date.matches(datePattern)) {
//            this.update_date = update_date;
//        }
//        else {
//            throw new IllegalArgumentException("Update date must be MM/DD/YYYY. Not a valid date.");
//        }
        this.update_date = update_date;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getWorkCenter() {
        return workCenter;
    }

    public void setWorkCenter(String workCenter) {
        this.workCenter = workCenter;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public ArrayList<String> getTeamList() {
        return teamList;
    }

    public void setTeamList(ArrayList<String> teamList) {
        this.teamList = teamList;
    }

    //    random uuid
    private static void generateUUID() {
        UUID uuid = UUID.randomUUID();
    }

}
