package com.nhom7.appqldt.ui_qlkh.ui.pheduyetdetai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.databinding.FragmentPheduyetdetaiBinding;

import java.util.List;

public class PheDuyetDeTaiFragment extends Fragment {
    TextView textView;
    private FragmentPheduyetdetaiBinding binding;
    RecyclerView recyclerView;
    List<SongModel> songList;
    SongAdapter songAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PheDuyetDeTaiViewModel homeViewModel =
                new ViewModelProvider(this).get(PheDuyetDeTaiViewModel.class);

        binding = FragmentPheduyetdetaiBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Do something with the view here
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}