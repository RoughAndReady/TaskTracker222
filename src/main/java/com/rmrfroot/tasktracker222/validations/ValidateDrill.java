package com.rmrfroot.tasktracker222.validations;

import javax.validation.constraints.NotEmpty;

public class ValidateDrill {
    @NotEmpty()
    private String event_title;

    @NotEmpty()
    private String start_date;

    @NotEmpty()
    private String deadline_date;

    @NotEmpty()
    private String location;

    @NotEmpty()
    private String admin_name;

    @NotEmpty()
    private String officer_email;

    @NotEmpty()
    private String created_timestamp;

    @NotEmpty()
    private String note;

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getDeadline_date() {
        return deadline_date;
    }

    public void setDeadline_date(String deadline_date) {
        this.deadline_date = deadline_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getOfficer_email() {
        return officer_email;
    }

    public void setOfficer_email(String officer_email) {
        this.officer_email = officer_email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getCreated_timestamp() {
        return created_timestamp;
    }

    public void setCreated_timestamp(String created_timestamp) {
        this.created_timestamp = created_timestamp;
    }

}
