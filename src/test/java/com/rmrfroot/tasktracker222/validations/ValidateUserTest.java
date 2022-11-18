package com.rmrfroot.tasktracker222.validations;

import com.rmrfroot.tasktracker222.entities.User;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;


import static org.junit.jupiter.api.Assertions.*;

class ValidateUserTest {

    private java.util.ArrayList<String> teamList =new ArrayList<>(Arrays.asList("team1", "team2"));

    private User user1 = new User("brianfrey", "Brian", "Frey",
            "root@gov.com","email@root.edu","email@root.edu",
            "9165555555","1234", "Captain",
            "workCenter","flight",teamList);

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @Test
    void getFirstName() {

        //user1.setFirstName("");
        user1.getFirstName();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }


    }

    @Test
    void setFirstName() {

        user1.setFirstName("Adam");
        user1.getFirstName();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }

    }

    @Test
    void getLastName() {

        user1.getLastName();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setLastName() {

        user1.setLastName("Lee");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getMilitaryEmail() {

        user1.getMilitaryEmail();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }

    }

    @Test
    void setMilitaryEmail() {

        user1.setMilitaryEmail("Mil@gov.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getCivilianEmail() {

        user1.getCivilianEmail();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);

        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }

    }

    @Test
    void setCivilianEmail() {

        user1.setCivilianEmail("civ@gov.com");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getPhoneNumber() {

        user1.getPhoneNumber();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setPhoneNumber() {

        user1.setPhoneNumber("916-555-5555");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }

    }

    @Test
    void getOfficeNumber() {
        user1.getOfficeNumber();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setOfficeNumber() {

        user1.setOfficeNumber("530-555-5555");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getRank() {

        user1.getRank();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setRank() throws FileNotFoundException {

        //user1.setRank("rank.txt");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getWorkCenter() {

        user1.getWorkCenter();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setWorkCenter() throws FileNotFoundException{

        //user1.setWorkCenter("workcenter.txt");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getFlight() {

        user1.getFlight();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setFlight() throws FileNotFoundException{

        user1.setFlight("flight.txt");
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void getTeams() {

        user1.getTeams();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }

    @Test
    void setTeams() {

        user1.setTeams(teamList);
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
        }
    }
}