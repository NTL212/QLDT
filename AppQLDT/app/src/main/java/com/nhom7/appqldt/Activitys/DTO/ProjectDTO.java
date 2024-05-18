package com.nhom7.appqldt.Activitys.DTO;

public class ProjectDTO {
    private String id;
    private String name;
    private TopicDTO topic;
    private LecturerDTO lecturer;
    private String statusForManager;
    private String statusForLecturer;

    public String getStatusForManager() {
        return statusForManager;
    }

    public void setStatusForManager(String statusForManager) {
        this.statusForManager = statusForManager;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public LecturerDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDTO lecturer) {
        this.lecturer = lecturer;
    }

    public String getStatusForLecturer() {
        return statusForLecturer;
    }

    public void setStatusForLecturer(String statusForLecturer) {
        this.statusForLecturer = statusForLecturer;
    }
}
