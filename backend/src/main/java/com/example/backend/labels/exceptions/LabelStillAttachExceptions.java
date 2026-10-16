package com.example.backend.labels.exceptions;

public class LabelStillAttachExceptions extends RuntimeException{
    public LabelStillAttachExceptions(Long labelId, Long taskId) {
        super("Label "+labelId+" has been still attached to "+taskId);
    }
}
