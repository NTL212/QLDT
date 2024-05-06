package com.nhom7.appqldt.ui_qlkh.ui.danhsachchude;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nhom7.appqldt.databinding.FragmentDanhsachchudeBinding;


public class DanhSachChuDeFragment extends Fragment {

    private DanhSachChuDeViewModel mViewModel;
    private FragmentDanhsachchudeBinding binding;
    public static DanhSachChuDeFragment newInstance() {
        return new DanhSachChuDeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DanhSachChuDeViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DanhSachChuDeViewModel.class);
        binding= FragmentDanhsachchudeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }



}