package com.nhom7.appqldt.ui_qlkh.ui.quanlydetai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nhom7.appqldt.databinding.FragmentQuanlydetaiBinding;


public class QuanLyDeTaiFragment extends Fragment {
    private  QuanLyDeTaiViewModel mViewModel;
    private FragmentQuanlydetaiBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QuanLyDeTaiViewModel dashboardViewModel =
                new ViewModelProvider(this).get(QuanLyDeTaiViewModel.class);

        binding = FragmentQuanlydetaiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}