package com.vinicius.cooperativevotes.model;

public class Exception {
    private String message;

    public Exception(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
