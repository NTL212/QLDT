package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom7.appqldt.R;


public class ThemChuDeDialogFragment extends DialogFragment {


    EditText edtMaSo, edtTenChuDe;

    public ThemChuDeDialogFragment() {
        // Required empty public constructor
    }

    public static ThemChuDeDialogFragment newInstance(String param1, String param2) {
        ThemChuDeDialogFragment fragment = new ThemChuDeDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_them_chu_de_dialog, container, false);


        edtMaSo = view.findViewById(R.id.edtMaSo);
        edtTenChuDe = view.findViewById(R.id.edtTenChuDe);

        view.findViewById(R.id.btnThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maSo = edtMaSo.getText().toString();
                String tenChuDe = edtTenChuDe.getText().toString();
                ListChuDeActivity activity = (ListChuDeActivity) getActivity();
                activity.themChuDe(maSo, tenChuDe);
                dismiss();

            }
        });





        return view;
    }
}