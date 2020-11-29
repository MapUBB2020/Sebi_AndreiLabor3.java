package com.company.FileRepo;

import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class TeacherFileRepository implements CrudFileRepository<Teacher> {

    @Override
    public List<Teacher>insert(List<Teacher> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Teacher.txt",true);
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
    public List<Teacher> read_to_list() {

        List<Teacher> teachers;
        try{
            FileInputStream fis = new FileInputStream("Student.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            teachers = (List) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Could not read list");
            return null;
        }
        return teachers;
    }

    @Override
    public List<Teacher> edit(List<Teacher> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Teacher.txt");
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
