package com.example.producer.dto;

public class HelloDto {

    private String message;

    public HelloDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
