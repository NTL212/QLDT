package com.nhom7.appqldt.Helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogHelper {
    public static void showDialog(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveButtonClickListener)
                .setCancelable(false) // Tùy chọn để ngăn người dùng bấm ra bên ngoài để đóng dialog
                .show();
    }
}
