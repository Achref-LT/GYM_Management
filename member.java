package com.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class member {
    private String name;
    private String cin;
    private int age;
    private int id;


    public int getAge() {
        return age;
    }
    public String getCin() {
        return cin;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
