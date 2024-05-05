package com.nhom7.appqldt.ui_qlkh.ui.danhsachchude;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DanhSachChuDeViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public DanhSachChuDeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fr2222agment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}