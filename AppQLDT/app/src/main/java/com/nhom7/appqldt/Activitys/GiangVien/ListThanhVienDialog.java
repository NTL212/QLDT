package com.nhom7.appqldt.Activitys.GiangVien;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Adapters.ThanhVienAdapter;
import com.nhom7.appqldt.Adapters.ThemSinhVienAdapter;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Student;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListThanhVienDialog extends DialogFragment {
    Context context;
    String projectCode;
    Button btnThemThanhVien;
    TextView tvSoLuongThanhVien;

    String role;

    int maxMember;

    public ListThanhVienDialog() {
    }

    public ListThanhVienDialog(Context context, String projectCode, String role, int maxMember) {
        this.context = context;
        this.projectCode= projectCode;
        this.role=role;
        this.maxMember = maxMember;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout, container, false);
        tvSoLuongThanhVien = view.findViewById(R.id.tvSoLuongThanhVien);
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<StudentDTO>>> call = apiService.getAllMemberOfProject(projectCode);
        call.enqueue(new Callback<APIResponse<List<StudentDTO>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<StudentDTO>>> call, Response<APIResponse<List<StudentDTO>>> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        List<StudentDTO> students = response.body().getResult();
                        if(students.size()>0){
                            tvSoLuongThanhVien.setVisibility(View.GONE);
                            if(students.size()>=maxMember){
                                btnThemThanhVien.setVisibility(View.GONE);
                                tvSoLuongThanhVien.setVisibility(View.VISIBLE);
                                tvSoLuongThanhVien.setText("Số lượng thành viên đạt tối đa");
                            }
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                        // Khởi tạo RecyclerView
                        RecyclerView recyclerView = view.findViewById(R.id.dialog_recyclerView);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        ThanhVienAdapter adapter = new ThanhVienAdapter(students, context, projectCode, getActivity().getSupportFragmentManager(), ListThanhVienDialog.this);
                        recyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<StudentDTO>>> call, Throwable t) {

            }
        });

        btnThemThanhVien = view.findViewById(R.id.btnThemThanhVien);
        if(role==null){
        }else {
            btnThemThanhVien.setVisibility(View.GONE);
        }
        btnThemThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ThemTVActivity.class);
                intent.putExtra("projectCode", projectCode); // Gắn dữ liệu vào Intent
                context.startActivity(intent);
                dismiss();
            }
        });

        return view;
    }
    private void loadThanhVien(){

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
