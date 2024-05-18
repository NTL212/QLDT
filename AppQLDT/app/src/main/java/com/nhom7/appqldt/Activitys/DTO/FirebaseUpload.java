package com.nhom7.appqldt.Activitys.DTO;

public class FirebaseUpload {

    private String projectCode;
    private String lectCode;
    private String url;
    private String date;

    private String filename;

    public FirebaseUpload() {
    }

    public FirebaseUpload(String projectCode, String lectCode, String url, String date, String filename) {
        this.projectCode = projectCode;
        this.lectCode = lectCode;
        this.url = url;
        this.date = date;
        this.filename = filename;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getLectCode() {
        return lectCode;
    }

    public void setLectCode(String lectCode) {
        this.lectCode = lectCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
