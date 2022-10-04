//package com.rmrfroot.tasktracker222.entities;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="User")
//public class User{
//
//    @Id
//    @GeneratedValue(strategy =GenerationType.IDENTITY)
//    @Column(name="id")
//    private int id;
//
//    @Column(name="email")
//    private String email;
//
//    @Column(name="first_name")
//    private String firstName;
//
//    @Column(name="last_name")
//    private String lastName;
//
//    @Column(name="register_date")
//    private String register_date;
//
//    @Column(name="update_date")
//    private String update_date;
//
//    public User() {
//    }
//
//    public User(String email, String firstName, String lastName, String register_date, String update_date) {
//        this.email = email;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.register_date = register_date;
//        this.update_date = update_date;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getRegister_date() {
//        return register_date;
//    }
//
//    public void setRegister_date(String register_date) {
//        this.register_date = register_date;
//    }
//
//    public String getUpdate_date() {
//        return update_date;
//    }
//
//    public void setUpdate_date(String update_date) {
//        this.update_date = update_date;
//    }
//}
