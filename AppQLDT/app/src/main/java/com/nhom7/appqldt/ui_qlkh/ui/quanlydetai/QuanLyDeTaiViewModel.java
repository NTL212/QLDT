package com.nhom7.appqldt.ui_qlkh.ui.quanlydetai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QuanLyDeTaiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public QuanLyDeTaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}