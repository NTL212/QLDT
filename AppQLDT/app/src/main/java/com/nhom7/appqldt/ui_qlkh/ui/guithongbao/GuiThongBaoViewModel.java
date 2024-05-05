package com.nhom7.appqldt.ui_qlkh.ui.guithongbao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GuiThongBaoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GuiThongBaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}