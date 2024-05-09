package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemDeTaiDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemDeTaiDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    EditText maDeTai, tenDeTai,chuDe,ngayMoDangKy, ngayKetThucDangKy, ngayNghiemThu, ngayHetHan,kinhPhiDuKien,soLuongThanhVienToiDa;

    Button btnThemDeTai;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThemDeTaiDialogFragment() {
    }

    public static ThemDeTaiDialogFragment newInstance(String param1, String param2) {
        ThemDeTaiDialogFragment fragment = new ThemDeTaiDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.dialogfragment_them_de_tai, container, false);
        maDeTai = view.findViewById(R.id.editTextMaDeTai);
        tenDeTai = view.findViewById(R.id.editTextTenDeTai);
        ngayMoDangKy = view.findViewById(R.id.editTextNgayMoDangKy);
        ngayKetThucDangKy = view.findViewById(R.id.editTextNgayKetThucDangKy);

        ngayNghiemThu = view.findViewById(R.id.editTextNgayNghiemThu);
        ngayHetHan = view.findViewById(R.id.editTextNgayHetHan);
        kinhPhiDuKien = view.findViewById(R.id.editTextKinhPhiDuKien);
        soLuongThanhVienToiDa = view.findViewById(R.id.editTextSoLuongTVToiDa);
        chuDe = view.findViewById(R.id.editTextChuDe);
        btnThemDeTai = view.findViewById(R.id.buttonThemDeTai);
        btnThemDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuanLyDeTaiActivity quanLyDeTaiActivity = (QuanLyDeTaiActivity) getActivity();
                quanLyDeTaiActivity.listDeTai.add(new DeTai("1adada","a232e","b","c"));
                quanLyDeTaiActivity.deTaiAdapter.notifyDataSetChanged();
                dismiss();
            }
        });
        return view;
    }
}