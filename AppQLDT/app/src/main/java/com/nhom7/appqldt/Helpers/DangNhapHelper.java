package com.nhom7.appqldt.Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.nhom7.appqldt.Activitys.DangNhapActivity;

public class DangNhapHelper {
    public static void DangXuat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLogin", false);
        editor.commit();
        Intent intent = new Intent(context, DangNhapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa stack các Activity và tạo một Task mới
        context.startActivity(intent);
    }
}
