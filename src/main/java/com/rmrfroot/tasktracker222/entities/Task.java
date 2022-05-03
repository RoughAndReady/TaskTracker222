package com.rmrfroot.tasktracker222.entities;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


    @Column(name="title")
    private String title;

    @Column(name="task_priority")
    private String task_priority;

    @Column(name="description")
    private String description;

    @Column(name="pdf_file")
    private String pdf_file;

    @Column(name="start_date")
    private Date startDate;

    @Column(name="deadline_date")
    private Date deadlineDate;

    @Column(name="note")
    private String note;

    public Task() {
    }

    public Task(int id, String title, String task_priority, String description, String pdf_file, Date startDate, Date deadlineDate, String note) {
        this.id = id;
        this.title = title;
        this.task_priority = task_priority;
        this.description = description;
        this.pdf_file = pdf_file;
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getTask_priority() {
        return task_priority;
    }
    public String getDescription() {
        return description;
    }
    public String getPdf_file() {
        return pdf_file;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getDeadlineDate() {
        return deadlineDate;
    }
    public String getNote() {
        return note;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask_priority(String task_priority) {
        this.task_priority = task_priority;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPdf_file(String pdf_file) {
        this.pdf_file = pdf_file;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setDeadlineDate(Date deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
