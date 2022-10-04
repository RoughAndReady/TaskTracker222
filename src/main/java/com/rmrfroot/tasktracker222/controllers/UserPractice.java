package com.rmrfroot.tasktracker222.controllers;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserPractice {

    @Id
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
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDate register_date) {
        this.register_date = register_date;
    }

    public LocalDate getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(LocalDate update_date) {
        this.update_date = update_date;
    }

    public UserPractice(int id, String email, String first_name, String last_name,
                        LocalDate register_date, LocalDate update_date) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.register_date = register_date;
        this.update_date = update_date;
    }

    public UserPractice() {

    }

//    random uuid
    static void uuid() {
        UUID uuid = UUID.randomUUID();
    }

}
