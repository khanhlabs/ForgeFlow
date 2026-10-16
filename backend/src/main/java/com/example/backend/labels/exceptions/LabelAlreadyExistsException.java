package com.example.backend.labels.exceptions;

public class LabelAlreadyExistsException extends RuntimeException
{
    public LabelAlreadyExistsException(String labelName)
    {
        super("Label with name " + labelName + " already exists in this project");
    }
}
