package com.company.model;

import java.io.Serializable;
import java.util.List;

public class Student extends Person implements Serializable {

    private long studentId;
    public int totalCredits;
    public List<Course> enrolledCourses;

    public Student(long studentId, String firstName, String lastName, int totalCredits, List<Course> enrolledCourses) {
        super(studentId, firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public Long getStudentId() {
        return studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", totalCredits=" + totalCredits +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
