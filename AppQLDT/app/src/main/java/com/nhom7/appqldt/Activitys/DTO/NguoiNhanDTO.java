package com.nhom7.appqldt.Activitys.DTO;

public class NguoiNhanDTO {
    private String id;
    private String name;

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

    public NguoiNhanDTO() {
    }

    public NguoiNhanDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
