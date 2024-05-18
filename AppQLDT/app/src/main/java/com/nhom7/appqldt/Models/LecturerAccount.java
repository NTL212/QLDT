package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class LecturerAccount implements Serializable {
    private Lecturer lecturer;
    private Account account;

    public LecturerAccount(Lecturer lecturer, Account account) {
        this.lecturer = lecturer;
        this.account = account;
    }

    public LecturerAccount() {
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
