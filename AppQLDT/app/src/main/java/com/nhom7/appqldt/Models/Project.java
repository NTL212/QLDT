package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class Project implements Serializable {
    private String projectCode;
    private String name;
    private String createDate;
    private String description;
    private int maxMember;
    private String openRegDate;
    private String closeRegDate;

    @Override
    public String toString() {
        return "Project{" +
                "projectCode='" + projectCode + '\'' +
                ", name='" + name + '\'' +
                ", createDate='" + createDate + '\'' +
                ", description='" + description + '\'' +
                ", maxMember=" + maxMember +
                ", openRegDate='" + openRegDate + '\'' +
                ", closeRegDate='" + closeRegDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", acceptanceDate='" + acceptanceDate + '\'' +
                ", estBudget=" + estBudget +
                ", result='" + result + '\'' +
                ", topic=" + topic +
                ", lecturer=" + lecturer +
                ", isProposed=" + isProposed +
                '}';
    }

    private String startDate;
    private String endDate;
    private String acceptanceDate;
    private double estBudget;
    private String result;
    private Topic topic;
    private Lecturer lecturer;
    private boolean isProposed;

    public Project() {
    }

    public Project(String projectCode, String name, String createDate, String description, int maxMember, String openRegDate, String closeRegDate, String startDate, String endDate, String acceptanceDate, double estBudget, String result, Topic topic, Lecturer lecturer, boolean isProposed) {
        this.projectCode = projectCode;
        this.name = name;
        this.createDate = createDate;
        this.description = description;
        this.maxMember = maxMember;
        this.openRegDate = openRegDate;
        this.closeRegDate = closeRegDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.acceptanceDate = acceptanceDate;
        this.estBudget = estBudget;
        this.result = result;
        this.topic = topic;
        this.lecturer = lecturer;
        this.isProposed = isProposed;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public String getOpenRegDate() {
        return openRegDate;
    }

    public String getCloseRegDate() {
        return closeRegDate;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public String getResult() {
        return result;
    }

    public boolean isProposed() {
        return isProposed;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getName() {
        return name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getEstBudget() {
        return estBudget;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setOpenRegDate(String openRegDate) {
        this.openRegDate = openRegDate;
    }

    public void setCloseRegDate(String closeRegDate) {
        this.closeRegDate = closeRegDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public void setProposed(boolean proposed) {
        isProposed = proposed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setEstBudget(double estBudget) {
        this.estBudget = estBudget;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
