package com.rmrfroot.tasktracker222.validations;


import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

public class ValidateUser {

    @NotEmpty()
    private String firstName;

    @NotEmpty()
    private String lastName;

    @NotEmpty()
    private String militaryEmail;

    @NotEmpty()
    private String civilianEmail;


    @NotEmpty()
    private String phoneNumber;

    @NotEmpty()
    private String officeNumber;

    @NotEmpty()
    private String rank;

    @NotEmpty()
    private String workCenter;

    @NotEmpty()
    private String flight;

    @NotEmpty()
    private ArrayList<String> teams;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMilitaryEmail() {
        return militaryEmail;
    }

    public void setMilitaryEmail(String militaryEmail) {
        this.militaryEmail = militaryEmail;
    }

    public String getCivilianEmail() {
        return civilianEmail;
    }

    public void setCivilianEmail(String civilianEmail) {
        this.civilianEmail = civilianEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
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

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }
}
