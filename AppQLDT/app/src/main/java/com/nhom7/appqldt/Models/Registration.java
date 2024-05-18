package com.nhom7.appqldt.Models;

public class Registration {
    private Lecturer lect;
    private Project proj;

    public Registration() {
    }

    public Registration(Lecturer lect, Project proj) {
        this.lect = lect;
        this.proj = proj;
    }

    public Lecturer getLect() {
        return lect;
    }

    public Project getProj() {
        return proj;
    }

    public void setLect(Lecturer lect) {
        this.lect = lect;
    }

    public void setProj(Project proj) {
        this.proj = proj;
    }
}
