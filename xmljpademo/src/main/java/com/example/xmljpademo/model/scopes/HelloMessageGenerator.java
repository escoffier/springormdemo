package com.example.xmljpademo.model.scopes;

public class HelloMessageGenerator {
    String message;

    public HelloMessageGenerator(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
