package com.rmrfroot.tasktracker222.DAO;

import com.rmrfroot.tasktracker222.Entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;


public interface DaysDAO extends JpaRepository<Day,Integer> {
    public interface Dao<T, I> {
        Optional<T> get(int id);
        Collection<T> getAll();
        Optional<I> save(T t);
        void update(T t);
        void delete(T t);
    }
}
