package com.nhom7.appqldt.Helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Adapters.ThanhVienAdapter;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DialogHelper {

    Context context;

    public DialogHelper() {
    }

    public DialogHelper(Context context) {
        this.context = context;
    }


    public static void showDialog(Context context, String title, String message, String positiveButtonText, DialogInterface.OnClickListener positiveButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveButtonClickListener)
                .setCancelable(false) // Tùy chọn để ngăn người dùng bấm ra bên ngoài để đóng dialog
                .show();
    }

    // Phương thức hiển thị dialog thành công với nội dung mặc định chỉ cần tham số message
    public static void showSuccessDialog(Context context, String message) {
        String title = "Thành công";
        String positiveButtonText = "OK";
        DialogInterface.OnClickListener positiveButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        showDialog(context, title, message, positiveButtonText, positiveButtonClickListener);
    }

    public static void showFailedDialog(Context context, String message) {
        String title = "Thất bại";
        String positiveButtonText = "OK";
        DialogInterface.OnClickListener positiveButtonClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };

        showDialog(context, title, message, positiveButtonText, positiveButtonClickListener);
    }

//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.dialog_layout, container, false);
//
//        List<ThanhVien> listTV = new ArrayList<>();
//        String dateString = "01/01/2002";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        Date date;
//        try {
//            date = dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//        listTV.add(new ThanhVien(1, "Nguyen Van A", "2011", date, "1A","CNTT"));
//        listTV.add(new ThanhVien(2, "Tran Thi B", "2012", date, "1A","CNTT"));
//        listTV.add(new ThanhVien(3, "Le Chi D", "2013", date, "1A","CNTT"));
//
//        // Khởi tạo RecyclerView
//        RecyclerView recyclerView = view.findViewById(R.id.dialog_recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ThanhVienAdapter adapter = new ThanhVienAdapter( listTV, context);
//        recyclerView.setAdapter(adapter);
//        return view;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        return super.onCreateDialog(savedInstanceState);
//    }
}
