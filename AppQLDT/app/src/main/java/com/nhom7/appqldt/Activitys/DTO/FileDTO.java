package com.nhom7.appqldt.Activitys.DTO;

import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Project;

public class FileDTO {
    private int fileId;
    private byte[] data;
    private String uploadTime;
    private Account uploader;
    private Project proj;

    public int getFileId() {
        return fileId;
    }

    public byte[] getData() {
        return data;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public Account getUploader() {
        return uploader;
    }

    public Project getProj() {
        return proj;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setUploader(Account uploader) {
        this.uploader = uploader;
    }

    public void setProj(Project proj) {
        this.proj = proj;
    }
}
