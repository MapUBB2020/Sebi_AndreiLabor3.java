package com.company.model;

import java.io.Serializable;

public class Person implements Serializable {

    long id;
    public String firstName;
    public String lastName;

    public Person() {
    }

    public Person(long Id, String firstName, String lastName) {
        this.id = Id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
