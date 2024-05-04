package com.nhom7.appqldt.Models;

public class MessageResponse {
    private String message;
    private String messId;

    public String getMessId() {
        return messId;
    }

    public void setMessId(String messId) {
        this.messId = messId;
    }

    // Getter và setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
