package com.nhom7.appqldt.Models;

public class APIResponse<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private T result;

    public APIResponse() {
    }

    public APIResponse(boolean success, int statusCode, String message, T result) {
        this.success = success;
        this.statusCode = statusCode;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
