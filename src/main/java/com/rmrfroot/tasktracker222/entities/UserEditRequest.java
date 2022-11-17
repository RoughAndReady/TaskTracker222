package com.rmrfroot.tasktracker222.entities;

import java.util.ArrayList;

public class UserEditRequest {

    public String id;
    public String firstName;
    public String lastName;
    public String email;
    public String militaryEmail;
    public String civilianEmail;
    public String phoneNumber;
    public String officeNumber;
    public String rank;
    public String workCenter;
    public String flight;
    public ArrayList<String> teams;

    public UserEditRequest(){

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
