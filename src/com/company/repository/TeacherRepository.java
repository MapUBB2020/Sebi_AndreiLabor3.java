package com.company.repository;

import com.company.model.Course;
import com.company.model.Teacher;

import java.util.List;

public class TeacherRepository implements ICrudRepository<Teacher>{

    private List<Teacher> teachers;

    public TeacherRepository(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    @Override
    public Teacher findOne(Long id) {
        for(Teacher teacher : teachers)
            if(teacher.getId().equals(id))
                return teacher;
        return null;
    }

    @Override
    public Iterable findAll() {
        return teachers;
    }

    @Override
    public Teacher save(Teacher entity) {
        if (teachers.contains(entity)) {
            return null;
        }
        else
            teachers.add(entity);
        return (Teacher) teachers;
    }

    @Override
    public Teacher delete(Long id) {
        if (teachers.contains(id)) {
            return null;
        }
        else
            teachers.remove(id);
        return (Teacher) teachers;
    }

    @Override
    public Teacher update(Teacher entity) {
        Teacher teacher = findOne(entity.getId());
        if(teacher != null) {
            teacher.setCourses(entity.getCourses());
            teacher.setFirstName(entity.getFirstName());
            teacher.setLastName(entity.getLastName());
            return null;
        }
        return entity;
    }

}
