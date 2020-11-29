package com.company.view;

import com.company.DataExceptions;
import com.company.controller.RegistrationSystem;
import com.company.model.Course;
import com.company.model.Student;
import com.company.repository.CourseRepository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    Scanner scan = new Scanner(System.in);
    RegistrationSystem regSys;

    public Menu(RegistrationSystem regSys) {
        this.regSys = regSys;
    }

    public void menu() throws DataExceptions {

        boolean opt = true;
        while(opt) {

            System.out.println("1 -> New student");
            System.out.println("2 -> Delete student");
            System.out.println("3 -> New teacher");
            System.out.println("4 -> New course");
            System.out.println("5 -> Delete course  ONLY FOR TEACHER!");
            System.out.println("6 -> Register for course");
            System.out.println("7 -> Show all courses");
            System.out.println("8 -> Courses with free places");
            System.out.println("9 -> Show enrolled students for a course");
            System.out.println("0 -> Exit");

            System.out.println("Choose your option: ");
            String option = scan.next();

            switch (option) {
                case "1" -> regSys.insert_stud();
                case "2" -> regSys.delete_stud();
                case "3" -> regSys.insert_teacher();
                case "4" -> regSys.insert_course();
                case "5" -> regSys.delete_course();
                case "6" -> opt6();
                case "7" -> regSys.getAllCourses();
                case "8" -> regSys.retrieveCoursesWithFreePlaces();
                case "9" -> opt9();
                case "0" -> opt = false;
            }
        }
    }

    public void opt6() throws DataExceptions {
        try{
        System.out.println("Insert student id");
        long id_student = scan.nextLong();
        regSys.register(id_student);}
        catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    public void opt9() throws DataExceptions {
        try {
            System.out.println("Insert course id to show enrolled students: ");
            long id_course = scan.nextLong();
            List<Student> enrolled_students = regSys.retrieveStudentsEnrolledForACourse(id_course);
            for (Student student : enrolled_students) {
                System.out.println(student.getStudentId() + " " + student.getFirstName() + " " + student.getLastName() + " " + student.getTotalCredits());
            }
        }
        catch (InputMismatchException e) {
            throw new DataExceptions("The course id should be of type long");}
    }
}

