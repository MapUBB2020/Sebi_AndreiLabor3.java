package com.company.FileRepo;

import com.company.model.Course;
import com.company.model.Person;
import com.company.model.Student;
import com.company.repository.CourseRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseFileRepository implements CrudFileRepository<Course> {


    @Override
    public List<Course> insert(List<Course> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Course.txt",true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entity);
            oos.close();
            fos.close();
            return entity;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Course> read_to_list() {

        List<Course> courses;
        try{
            FileInputStream fis = new FileInputStream("Course.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courses = (List) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Could not read list");
            return null;
        }
        return courses;
    }

    @Override
    public List<Course> edit(List<Course> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Course.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(entity);
            oos.close();
            fos.close();
            return entity;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }
}

