package com.company.repository;

import com.company.model.Course;
import com.company.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepository implements ICrudRepository<Student> {

    private List<Student> students;

    public StudentRepository(List<Student> student) {
        this.students = student;
    }

    public void setCourse(List<Student> student) {
        this.students = student;
    }

    public List<Student> getCourse() {
        return students;
    }

    @Override
    public Student findOne(Long id) {
        for(Student student : students)
            if(student.getStudentId() == id)
                return student;
       return null;
    }

    @Override
    public Iterable findAll() {
        return students;
    }

    @Override
    public Student save(Student entity) {
        if (students.contains(entity)) {
            return null;
        }
        else
            students.add(entity);
        return (Student) students;
    }

    @Override
    public Student delete(Long id) {
        if (students.contains(id)) {
            return null;
        }
        else
           students.remove(id);
        return (Student) students;
    }

    @Override
    public Student update(Student entity) {
       Student student = findOne(entity.getStudentId());
       if(student != null){
           student.setEnrolledCourses(entity.getEnrolledCourses());
           student.setTotalCredits(entity.getTotalCredits());
           student.setFirstName(entity.getFirstName());
           student.setLastName(entity.getLastName());
           return null;
       }
       return entity;
    }
}
