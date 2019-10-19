package com.example.xmljpademo.model.scopes;

public class Person {
    private String name;

    public Person() {
        name = "robbie";
    }
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
