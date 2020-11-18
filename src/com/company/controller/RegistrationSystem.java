package com.company.controller;

import com.company.model.Course;
import com.company.model.Student;
import com.company.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class RegistrationSystem {

    CourseRepository courses;

    public RegistrationSystem(CourseRepository courses) {
        this.courses = courses;
    }

    public boolean register(Course course, Student student){
        for(int i=0; i <= course.getStudentsEnrolled().size()-1; i++) {
            if (student == course.getStudentsEnrolled().get(i)) {
                System.out.println("You are enrolled in this course. Choose another one");
                return false;
            }
        }

        if(course.getStudentsEnrolled().size() == course.getMaxEnrolled()){
                    System.out.println("The course you are trying to join is full");
                    return false;
            }
        else if(student.getTotalCredits() + course.getCredits() > 30){
            System.out.println("You already have 30 credits");
            return false;
        }
        else {
            course.addStudent(student);
            student.setTotalCredits(student.getTotalCredits() + course.getCredits());
            return true;
        }
    }

    public List<Course> retrieveCoursesWithFreePlaces(){

        List<Course> freePlace = new ArrayList<>();

        for(int i=0; i <= courses.getCourse().size()-1; i++){
            if(courses.getCourse().get(i).getStudentsEnrolled().size()<courses.getCourse().get(i).getMaxEnrolled()){
                freePlace.add(courses.getCourse().get(i));
            }
        }
        return freePlace;
    }

    public List<Student> retrieveStudentsEnrolledForACourse(String course){

        List<Student> students = new ArrayList<>();

        for(int i=0; i <= courses.getCourse().size()-1; i++){
            if(courses.getCourse().get(i).getName().equals(course)){
                students.add((Student) courses.getCourse().get(i).getStudentsEnrolled());
            }
            else{
                return null;
            }
        }
        return students;
    }

    public List<Course> getAllCourses(){

        List<Course> cursuri = new ArrayList<>();

        for(int i=0; i <= courses.getCourse().size()-1; i++){
            cursuri.add(courses.getCourse().get(i));
        }
        return cursuri;
    }

}
