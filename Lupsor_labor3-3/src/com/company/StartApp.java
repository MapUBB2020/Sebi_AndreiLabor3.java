package com.company;

import com.company.FileRepo.CourseFileRepository;
import com.company.FileRepo.StudentFileRepository;
import com.company.FileRepo.TeacherFileRepository;
import com.company.controller.RegistrationSystem;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;
import com.company.view.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) throws DataExceptions {

        System.out.println("Start point");

        List<Course> courses = new ArrayList<>();
        List<Teacher> teachers = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        CourseRepository courseRepo = new CourseRepository(courses);
        TeacherRepository teacherRepo = new TeacherRepository(teachers);
        StudentRepository studentRepo = new StudentRepository(students);
        CourseFileRepository courseFile = new CourseFileRepository();
        TeacherFileRepository teacherFile = new TeacherFileRepository();
        StudentFileRepository studentFile = new StudentFileRepository();
        RegistrationSystem regSys = new RegistrationSystem(courseRepo, teacherRepo, studentRepo, courseFile, teacherFile, studentFile);
        Menu menu = new Menu(regSys);

        System.out.println("Cursurile filtrate dupa numarul de credite:");
        System.out.println(RegistrationSystem.Filtercredits(courses));
        System.out.println("Studentii sortati alfabetic dupa numele lor:");
        Collections.sort(students, new RegistrationSystem.NameSorter());
        System.out.println(students);

        menu.menu();

    }
}
