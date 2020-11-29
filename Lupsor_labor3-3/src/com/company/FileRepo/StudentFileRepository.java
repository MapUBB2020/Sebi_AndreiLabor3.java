package com.company.FileRepo;

import com.company.model.Course;
import com.company.model.Student;
import com.company.repository.StudentRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentFileRepository implements CrudFileRepository<Student> {

    @Override
    public List<Student> insert(List<Student> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Student.txt",true);
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
    public List<Student> read_to_list() {

        List<Student> students;
        try{
            FileInputStream fis = new FileInputStream("Student.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (List) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Could not read list");
            return null;
        }
        return students;
    }

    @Override
    public List<Student> edit(List<Student> entity) {

        try {
            FileOutputStream fos = new FileOutputStream("Student.txt");
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