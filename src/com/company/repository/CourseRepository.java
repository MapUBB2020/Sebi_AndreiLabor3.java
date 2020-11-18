package com.company.repository;

import com.company.model.Course;

import java.util.List;


public class CourseRepository implements ICrudRepository<Course> {

    private List<Course> course;

    public CourseRepository(List<Course> course) {
        this.course = course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }
    public List<Course> getCourse() {
        return course;
    }

    @Override
    public Course findOne(Long id) {
        //Course course = null;
        for (int i=0; i<=course.size()-1;i++) {
            if (course.get(i).getId().equals(id))
                return course.get(i);
        }
        return null;
    }

    @Override
    public Iterable findAll() {
        return course;
    }

    @Override
    public Course save(Course entity) {
        if (course.contains(entity)) {
            return null;
        }
        else
            course.add(entity);
        return (Course) course;
    }

    @Override
    public Course delete(Long id) {
        if (course.contains(id)) {
            return null;
        }
        else
            course.remove(id);
        return (Course) course;
    }

    @Override
    public Course update(Course entity) {
        Course course = findOne(entity.getId());
        if(course != null){
            course.setCredits(entity.getCredits());
            course.setStudentsEnrolled(entity.getStudentsEnrolled());
            course.setMaxEnrolled(entity.getMaxEnrolled());
            course.setTeacher(entity.getTeacher());
            course.setName(entity.getName());
            return null;
        }
        return entity;
    }
}
