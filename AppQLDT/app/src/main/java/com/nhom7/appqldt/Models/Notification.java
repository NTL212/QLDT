package com.nhom7.appqldt.Models;

public class Notification {
    private int id;
    private Account sender;
    private Account receiver;
    private String title;
    private String content;
    private String sentTime;

    public Notification() {
    }

    public Notification(int id, Account sender, Account receiver, String title, String content, String sentTime) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.sentTime = sentTime;
    }

    public int getId() {
        return id;
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getSentTime() {
        return sentTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSentTime(String sentTime) {
        this.sentTime = sentTime;
    }
}
