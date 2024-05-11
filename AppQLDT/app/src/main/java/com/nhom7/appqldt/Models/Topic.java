package com.nhom7.appqldt.Models;

import java.io.Serializable;

public class Topic implements Serializable {
    private String topicCode;
    private String name;
    private boolean isEnabled;

    public Topic() {
    }

    public Topic(String topicCode, String name, boolean isEnabled) {
        this.topicCode = topicCode;
        this.name = name;
        this.isEnabled = isEnabled;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
