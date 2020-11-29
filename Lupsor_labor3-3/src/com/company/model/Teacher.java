package com.company.model;

import java.io.Serializable;
import java.util.List;

public class Teacher extends Person implements Serializable {

    private long id;
    public List<Course> courses;

    public Teacher(long id, String firstName, String lastName, List<Course> courses) {
        super(id, firstName, lastName);
        this.id = id;
        this.courses = courses;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String toString() {
        return "Teacher{id= " + id +
                ", courses=" + courses +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
