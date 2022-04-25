package com.rmrfroot.tasktracker222.Entity;


import com.rmrfroot.tasktracker222.DAO.DaysDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import javax.persistence.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Entity
@Table(name="days")
public class Day implements DaysDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    public Day() {
    }

    public Day(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //updates
//    public String deleteEmployeeById(int id) throws SQLException {
//        getDBConnection();
//        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
//                ResultSet.CONCUR_READ_ONLY);
//        sqlString = "DELETE FROM Employees WHERE employee_id = " + id;
//        System.out.println("\nExecuting: " + sqlString);
//        stmt.execute(sqlString);
//        return "success";
//    }

    private void getDBConnection() {
    }


    @Override
    public String toString() {
        return "account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public List<Day> findAll() {
        return null;
    }

    @Override
    public List<Day> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Day> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Day> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Day entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Day> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Day> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Day> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Day> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Day> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Day> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Day> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Day getOne(Integer integer) {
        return null;
    }

    @Override
    public Day getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Day> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Day> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Day> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Day> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Day> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Day> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Day, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
