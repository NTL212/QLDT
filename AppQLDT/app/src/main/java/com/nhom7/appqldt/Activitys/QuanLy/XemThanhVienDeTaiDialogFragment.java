package com.nhom7.appqldt.Activitys.QuanLy;



import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhom7.appqldt.Models.ThanhVienDeTai;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class XemThanhVienDeTaiDialogFragment extends DialogFragment {
    List<ThanhVienDeTai> thanhVienDeTaiList;
    RecyclerView recyclerView;
    ThanhVienDeTaiAdapter thanhVienDeTaiAdapter;

    public XemThanhVienDeTaiDialogFragment() {
        // Required empty public constructor
    }

    public static XemThanhVienDeTaiDialogFragment newInstance(String param1, String param2) {
        XemThanhVienDeTaiDialogFragment fragment = new XemThanhVienDeTaiDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_xem_thanh_vien_de_tai_dialog, container, false);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        recyclerView = view.findViewById(R.id.recycler_view_thanh_vien_de_tai);
        thanhVienDeTaiList = new ArrayList<>();
        thanhVienDeTaiList.add(new ThanhVienDeTai(1,"Nguyễn Văn A","B17DCCN001","17DTHA","CNTT", "2020-01-01"));
        thanhVienDeTaiList.add(new ThanhVienDeTai(2,"Nguyễn Văn B","B17DCCN002","17DTHA","CNTT", "2020-01-01"));
        thanhVienDeTaiAdapter = new ThanhVienDeTaiAdapter(thanhVienDeTaiList, this.getContext());
        recyclerView.setAdapter(thanhVienDeTaiAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        thanhVienDeTaiAdapter.notifyDataSetChanged();



        return view;
    }
}