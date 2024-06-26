package com.nhom7.appqldt.Models;

import com.nhom7.appqldt.Activitys.DTO.LecturerDTO;

import java.io.Serializable;

public class DeTaiCanPheDuyet implements Serializable {
    private String id;
    private String name;
    private Topic topic;
    private LecturerDTO lecturer;
//    private boolean status;
    private String status;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LecturerDTO getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDTO lecturer) {
        this.lecturer = lecturer;
    }

//    public boolean isStatus() {
//        return status;
//    }
//
//    public void setStatus(boolean status) {
//        this.status = status;
//    }

//    public DeTaiCanPheDuyet(String id, String name, Topic topic, Lecturer lecturer, boolean status) {
//        this.id = id;
//        this.name = name;
//        this.topic = topic;
//        this.lecturer = lecturer;
//        this.status = status;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeTaiCanPheDuyet(String id, String name, Topic topic, LecturerDTO lecturer, String status) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.lecturer = lecturer;
        this.status = status;
    }
}
