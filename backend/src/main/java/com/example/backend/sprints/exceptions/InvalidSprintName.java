package com.example.backend.sprints.exceptions;

public class InvalidSprintName extends RuntimeException{
    public InvalidSprintName(String name) {
        super("Sprint name " + name + " already exists");
    }
}
