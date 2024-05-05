package com.nhom7.appqldt.ui_qlkh.ui.pheduyetdetai;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PheDuyetDeTaiViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PheDuyetDeTaiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fr2222agment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}