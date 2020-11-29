package com.company;

public class DataExceptions extends Exception{
    public DataExceptions(String Message){
        super(Message);
        System.out.println(Message);
    }
}
