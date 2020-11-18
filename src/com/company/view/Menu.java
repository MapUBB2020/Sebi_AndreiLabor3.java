package com.company.view;

import com.company.controller.RegistrationSystem;
import com.company.model.Course;
import com.company.model.Student;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {

    Scanner scan = new Scanner(System.in);
    RegistrationSystem regSys;
    CourseRepository courseRepo;

    public Menu(RegistrationSystem regSys) {
        this.regSys = regSys;
    }

    public void menu(){

        boolean opt = true;
        while(opt) {
            System.out.println("1 -> Register for course");
            System.out.println("2 -> Show all courses");
            System.out.println("3 -> Courses with free places");
            System.out.println("4 -> Show enrolled students");
            System.out.println("5 -> Delete courses");
            System.out.println("0 -> Exit");

            System.out.println("Choose your option: ");
            String option = scan.next();

            switch (option) {
                case "1" -> {
                    System.out.println("Insert firstname: ");
                    String firstname = scan.nextLine();

                    System.out.println("Insert lastname: ");
                    String lastname = scan.nextLine();

                    Student stud = new Student(firstname, lastname, 111L, 15, new ArrayList<Course>());

                    opt1(stud);
                }
                case "2" -> opt2();
                case "3" -> opt3();
                case "4" -> opt4();
                case "5" -> opt5();
                case "0" -> opt = false;
            }
        }
    }

    public void printCourses(List<Course> courses){
        for (Course cours : courses) {
            System.out.println(cours.getName());
        }
    }

    public void opt1(Student student){

        List<Course> courses = regSys.retrieveCoursesWithFreePlaces();

        System.out.println("Choose course: ");
        String curs = scan.next();

        List<String> coursesNames = courses.stream().map(Course::getName).collect(Collectors.toList());

        if(coursesNames.stream().noneMatch(x -> x.equalsIgnoreCase(curs))){
            System.out.println("Invalid course name!");
        }
        else{
            Course course = courses.stream().filter(x -> x.getName().equalsIgnoreCase(curs)).collect(Collectors.toList()).get(0);
            regSys.register(course, student);
        }
    }

    public void opt2(){
        for(int i=0; i<=regSys.getAllCourses().size()-1; i++){
            System.out.println(regSys.getAllCourses().get(i).getName() + ' ' + regSys.getAllCourses().get(i).getCredits() + ' ' + regSys.getAllCourses().get(i).getMaxEnrolled());
        }
    }

    public void opt3(){

        List<Course> courses = new ArrayList<>();
        courses = regSys.retrieveCoursesWithFreePlaces();

        for(Course c : courses){
            System.out.println(c.getName());
        }
    }

    public void opt4(){

        System.out.println("Insert course to show enrolled students: ");
        String course = scan.next();
        List<Student> student = new ArrayList<>(regSys.retrieveStudentsEnrolledForACourse(course));

        for(Student s : student){
            System.out.println(s.getFirstName() + ' ' + s.getLastName());
        }
    }

    public void opt5(){

        System.out.println("Enter password: ");
        String pass = scan.next();
        printCourses(courseRepo.getCourse());
        String option = scan.next();

        if(courseRepo.getCourse().stream().noneMatch(x -> x.getName().equalsIgnoreCase(option))){
            System.out.println("Invalid course name!");
        }
        else{
            int courseId = 0;
            for(int i = 0; i < courseRepo.getCourse().size(); i++){
                if(courseRepo.getCourse().get(i).getName().equalsIgnoreCase(option)){
                    courseId = i;
                    break;
                }
            }
            //Delete the course from every student's course list
            for(Student stud : courseRepo.getCourse().get(courseId).getStudentsEnrolled()){
                stud.deleteCourse(courseRepo.getCourse().get(courseId));
            }

            //Delete the course
            courseRepo.delete((long) courseId);
        }
    }
}

