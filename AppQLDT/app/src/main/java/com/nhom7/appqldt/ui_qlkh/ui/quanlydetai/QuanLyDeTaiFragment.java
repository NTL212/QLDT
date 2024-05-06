package com.nhom7.appqldt.ui_qlkh.ui.quanlydetai;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.R;
import com.nhom7.appqldt.databinding.FragmentQuanlydetaiBinding;

import java.util.ArrayList;
import java.util.List;


public class QuanLyDeTaiFragment extends Fragment {
    private  QuanLyDeTaiViewModel mViewModel;
    RecyclerView recyclerView;
    DeTaiAdapter deTaiAdapter;
    List<DeTaiModel> listDeTai;
    Button btnThemDeTai;
    private FragmentQuanlydetaiBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QuanLyDeTaiViewModel quanLyDeTaiViewModel =
                new ViewModelProvider(this).get(QuanLyDeTaiViewModel.class);
        mViewModel = new ViewModelProvider(this).get(QuanLyDeTaiViewModel.class);


        binding = FragmentQuanlydetaiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        listDeTai = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recycler_view);
        listDeTai.add(new DeTaiModel("1","De Tai 1","CD1","Chua Duyet"));
        listDeTai.add(new DeTaiModel("2","De Tai 2","CD2","Da Duyet"));
        listDeTai.add(new DeTaiModel("3","De Tai 3","CD3","Chua Duyet"));
        listDeTai.add(new DeTaiModel("4","De Tai 4","CD4","Da Duyet"));
        deTaiAdapter = new DeTaiAdapter(getContext(),listDeTai);
        recyclerView.setAdapter(deTaiAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        //live data
        quanLyDeTaiViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            listDeTai.add(new DeTaiModel("5","De Tai 5","CD5","Chua Duyet"));
        });
        btnThemDeTai = root.findViewById(R.id.btnThemDeTai);
        btnThemDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThemDeTaiDialogFragment themDeTaiDialogFragment = new ThemDeTaiDialogFragment();
                themDeTaiDialogFragment.show(getParentFragmentManager(),"Them De Tai");




                deTaiAdapter.notifyDataSetChanged();
            }
        });
        return root;


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}