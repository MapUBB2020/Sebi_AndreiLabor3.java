package com.company.controller;

import com.company.DataExceptions;
import com.company.FileRepo.CourseFileRepository;
import com.company.FileRepo.StudentFileRepository;
import com.company.FileRepo.TeacherFileRepository;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseRepository;
import com.company.repository.StudentRepository;
import com.company.repository.TeacherRepository;

import java.util.*;

public class RegistrationSystem {

    Scanner scan = new Scanner(System.in);
    CourseRepository courseRepo;
    TeacherRepository teacherRepo;
    StudentRepository studentRepo;

    CourseFileRepository courseFile;
    TeacherFileRepository teacherFile;
    StudentFileRepository studentFile;

    List<Course> courses = new ArrayList<>();
    List<Teacher> teachers = new ArrayList<>();
    List<Student> students = new ArrayList<>();


    public RegistrationSystem(CourseRepository courseRepo, TeacherRepository teacherRepo, StudentRepository studentRepo, CourseFileRepository courseFile, TeacherFileRepository teacherFile, StudentFileRepository studentFile ) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
        this.studentRepo = studentRepo;

        this.courseFile = courseFile;
        this.teacherFile = teacherFile;
        this.studentFile = studentFile;
    }

    public void insert_stud() throws DataExceptions {
        try {
            System.out.println("Insert student id: ");
            long id = scan.nextLong();
            System.out.println("Insert firstname: ");
            String firstname = scan.next();
            System.out.println("Insert lastname: ");
            String lastname = scan.next();


        //inseram in fisier si golim lista
        Student s = new Student(id, firstname, lastname, 0, new ArrayList<Course>());
        students.add(s);
        studentFile.insert(students);
        students.clear();}
        catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    public void delete_stud() throws DataExceptions {
        try {
            System.out.println("Insert student id you want to delete");
            long id_stud = scan.nextLong();

            //preluam studentii intr-o lista
            students = studentFile.read_to_list();

            //parcurgem lista
            for (Student student : students) {
                //daca exista atunci il eliminam din lista si rescriem in fisier, dar modificam si cursurile care contin acest student
                if (student.getStudentId() == (id_stud)) {
                    //citim cursurile intr-o lista
                    courses = courseFile.read_to_list();
                    for (Course course : courses) {
                        //verificam daca studentul a participat la acest curs, iar in caz ca da, atunci il eliminam din curs
                        if (course.getStudentsEnrolled().contains(student)) {
                            List<Student> to_delete = course.getStudentsEnrolled();
                            to_delete.remove(student);
                            courseFile.edit(courses);
                        }
                    }
                    students.remove(student);
                    studentFile.edit(students);
                    courses.clear();
                    students.clear();
                } else {
                    System.out.println("Student does not exist");
                }
            }
        }catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    public void insert_teacher() throws DataExceptions {
        try{
        //datele profesorului
        System.out.println("Insert teacher id: ");
        long id = scan.nextLong();
        System.out.println("Insert firstname: ");
        String firstname = scan.next();
        System.out.println("Insert lastname: ");
        String lastname = scan.next();

        //inseram in fisier si golim lista
        Teacher t = new Teacher(id, firstname, lastname, new ArrayList<Course>());
        teachers.add(t);
        teacherFile.insert(teachers);
        teachers.clear();}
        catch (InputMismatchException e) {
            throw new DataExceptions("The teacher id should be of type long");}
    }

    public void insert_course() throws DataExceptions {
        try {
            System.out.println("Insert teacher id: ");
            long id_teacher = scan.nextLong();

            //preluam toti profesorii din lista
            teachers = teacherFile.read_to_list();

            //verificam daca exista profesorul in baza de date
            for (Teacher teacher : teachers) {

                //daca da, atunci putem creeea  un curs nou si sa il introducem in baza de date
                if (teacher.getId() == id_teacher) {
                    System.out.println("Insert course id: ");
                    long id_course = scan.nextLong();
                    System.out.println("Insert course title: ");
                    String name = scan.next();
                    System.out.println("Insert maximum places for this course: ");
                    int enrolled_places = scan.nextInt();
                    System.out.println("Insert credits for this course: ");
                    int credits = scan.nextInt();

                    //inseram in fisier si golim lista
                    Course newCourse = new Course(id_course, name, teacher, enrolled_places, new ArrayList<>(), credits);
                    courses.add(newCourse);
                    courseFile.insert(courses);
                    courses.clear();
                } else {
                    System.out.println("Teacher id does not exist");
                }
            }
        }catch (InputMismatchException e) {
            throw new DataExceptions("The course id should be of type long");}
    }

    public void delete_course(){

        System.out.println("Insert teacher id");
        long id_teacher = scan.nextLong();

        teachers = teacherFile.read_to_list();

        //Cautam profesorul a carui curs il vom sterge
        for(Teacher teacher : teachers) {
            if (teacher.getId().equals(id_teacher)) {
                List<Course> teacher_course = teacher.getCourses();
                System.out.println(teacher_course);

                System.out.println("Insert course id you want to delete");
                long id_course = scan.nextLong();

                //Eliminam cursul din lista profesorului
                teacher_course.removeIf(tc -> tc.getId().equals(id_course));
                teacherFile.edit(teachers);

                //preluam cursurile din baza de date intr-o lista
                courses = courseFile.read_to_list();

                //parcurgem lista
                for (Course course : courses) {
                    //daca exista atunci il eliminam din lista si rescriem in fisier, dar modificam si studentii + creditele daca acestia au fost inscrisi
                    if (course.getId().equals(id_course)) {
                        List<Student> students_enrolled = course.getStudentsEnrolled();
                        students = studentFile.read_to_list();
                        //actualizam studentul si il scriem in baza de date
                        for (Student student : students_enrolled) {
                            for (Student student1 : students) {
                                if (student1.getStudentId()==(student.getStudentId())) {
                                    student1.setTotalCredits(student1.getTotalCredits() - course.getCredits());
                                    List<Course> to_delete = student1.getEnrolledCourses();
                                    to_delete.remove(course);
                                }
                            }
                            studentFile.edit(students);
                        }
                        courses.remove(course);
                        courseFile.edit(courses);
                        students.clear();
                        courses.clear();
                    } else {
                        System.out.println("Course does not exist");
                    }
                }
            }
        }
        teachers.clear();
    }


    public boolean register(long id_student) throws DataExceptions {
        try {
            students = studentFile.read_to_list();
            courses = courseFile.read_to_list();

            List<Course> courses_with_places = retrieveCoursesWithFreePlaces();
            for (Course course : courses_with_places) {
                System.out.println(course.getId() + "  " + course.getName());
            }

            System.out.println("Insert course id from above list");
            long id_cours = scan.nextLong();

            for (Course course : courses) {
                if (course.getId().equals(id_cours)) {

                    List<Student> enrolled = course.getStudentsEnrolled();

                    //verificam daca studentul este deja inscris
                    for (Student student : enrolled) {
                        if (student.getStudentId() == (id_student)) {
                            System.out.println("You are enrolled in this course. Choose another one");
                            return false;
                        }
                    }

                    //verificam daca mai sunt locuri
                    if (course.getStudentsEnrolled().size() == course.getMaxEnrolled()) {
                        System.out.println("The course you are trying to join is full");
                        return false;
                    }

                    //verificam daca studentul va vaea in urma adaugarii un numar mai mare de 30 de credite
                    else {
                        for (Student student : students) {
                            if (student.getStudentId() == (id_student)) {
                                if (student.getTotalCredits() + course.getCredits() > 30) {
                                    System.out.println("If you join this course tou will pass the maximum numbar of allowed credits");
                                    return false;
                                }
                            }
                        }
                    }
                    //Adaugam studentul in lista de studenti al cursului
                    for (Student student : students) {
                        if (student.getStudentId() == (id_student)) {
                            enrolled.add(student);
                            student.setTotalCredits(student.getTotalCredits() + course.getCredits());
                        }
                    }
                    courseFile.edit(courses);
                    studentFile.edit(students);
                    return true;
                }
            }
            return false;
        }catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    public List<Course> retrieveCoursesWithFreePlaces() throws DataExceptions{
        try {
            //lista pentru cursurile cu locuri libere
            List<Course> freePlace = new ArrayList<>();

            //cursurile din baza de date le stocam in lista si in courseRepo
            courses = courseFile.read_to_list();

            //verificam care curs are locuri libere
            for (Course course : courses) {
                if (course.getStudentsEnrolled().size() < course.getMaxEnrolled()) {
                    freePlace.add(course);
                }
            }
            return freePlace;
        }catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    /**
     *
     * @param id_course
     * @return lista de studenti in caz ca nu este null si in caz ca cursul exista
     */
    public List<Student> retrieveStudentsEnrolledForACourse(long id_course) throws DataExceptions {
        try {
            courses = courseFile.read_to_list();

            for (Course course : courses) {
                if (course.getId().equals(id_course)) {
                    return course.getStudentsEnrolled();
                }
            }
            return null;
        }catch (InputMismatchException e) {
            throw new DataExceptions("The student id should be of type long");}
    }

    public List<Course> getAllCourses(){

        courses = courseFile.read_to_list();
        return courses;
    }

    public static int Filtercredits(List<Course> courses){
        long size = courses.stream().filter(a->a.getCredits()>5).count();
        return (int) size;
    }
    public static class NameSorter implements Comparator<Student>
    {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.getFirstName().compareToIgnoreCase(s2.getFirstName());
        }
    }
}
