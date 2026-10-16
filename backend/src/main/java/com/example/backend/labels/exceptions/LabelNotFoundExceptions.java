package com.example.backend.labels.exceptions;

public class LabelNotFoundExceptions extends RuntimeException
{
    public LabelNotFoundExceptions(Long labelId)
    {
        super("Label with id " + labelId + " not found");
    }
}
