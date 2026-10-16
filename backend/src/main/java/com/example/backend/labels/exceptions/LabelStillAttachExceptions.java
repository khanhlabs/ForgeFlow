package com.example.backend.labels.exceptions;

public class LabelStillAttachExceptions extends RuntimeException{
    public LabelStillAttachExceptions(Long labelId) {
        super("Label "+labelId+" has been still attached to task");
    }
}
