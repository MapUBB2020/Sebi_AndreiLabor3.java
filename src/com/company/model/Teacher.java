package com.company.model;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person{

    private Long id;
    public List<Course> courses;

    public Teacher() {
    }

    public Teacher(String firstName, String lastName, Long id, List<Course> courses) {
        super(firstName, lastName);
        this.id = id;
        this.courses = courses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void deleteCourse(Course curs){

        for(int i=0; i <= curs.getStudentsEnrolled().size(); i++){
            curs.getStudentsEnrolled().get(i).enrolledCourses.remove(curs);
            curs.getStudentsEnrolled().get(i).setTotalCredits(curs.getStudentsEnrolled().get(i).getTotalCredits()-curs.getCredits());
        }
        courses.remove(curs);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + courses +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
