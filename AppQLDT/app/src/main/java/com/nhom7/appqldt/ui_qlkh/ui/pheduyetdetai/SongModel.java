package com.nhom7.appqldt.ui_qlkh.ui.pheduyetdetai;

public class SongModel {
    String id,name;

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

    public SongModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
