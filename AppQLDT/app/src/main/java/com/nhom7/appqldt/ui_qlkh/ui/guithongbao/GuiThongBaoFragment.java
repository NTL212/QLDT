package com.nhom7.appqldt.ui_qlkh.ui.guithongbao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.nhom7.appqldt.databinding.FragmentGuithongbaoBinding;


public class GuiThongBaoFragment extends Fragment {

    private FragmentGuithongbaoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GuiThongBaoViewModel notificationsViewModel =
                new ViewModelProvider(this).get(GuiThongBaoViewModel.class);

        binding = FragmentGuithongbaoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}