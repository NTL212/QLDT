package com.nhom7.appqldt.Activitys.QuanLy;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.ProjectChiTietQL;
import com.nhom7.appqldt.R;

import java.text.DecimalFormat;

import retrofit2.Call;

public class ChiTietDTQuanLyActivity extends AppCompatActivity {
    Button btnSuaDeTai, btnxoadeTai;
    String maDeTai;

    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang, tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa;


    ProjectChiTietQL project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dtquan_ly);
        maDeTai = getIntent().getStringExtra("maDeTai");
        Toast.makeText(this, maDeTai, Toast.LENGTH_SHORT).show();
        ProjectChiTietQL deTai = new ProjectChiTietQL();
        deTai.setId(getIntent().getStringExtra("maDeTai"));
        tvChiTietMaDeTai = findViewById(R.id.tvChiTietMaDeTai);
        tvChiTietTenDeTai = findViewById(R.id.tvChiTietTenDeTai);
        tvChiTietChuDe = findViewById(R.id.tvChiTietChuDe);

        tvChiTietNgayDang = findViewById(R.id.tvChiTietNgayDang);

        tvChiTietNgayMoDang = findViewById(R.id.tvChiTietNgayMoDang);
        tvChiTietNgayKetThucDang = findViewById(R.id.tvChiTietNgayKetThucDang);

        tvChiTietNgayBatDau = findViewById(R.id.tvChiTietNgayBatDau);
        tvChiTietNgayKetThuc = findViewById(R.id.tvChiTietNgayKetThuc);
        tvChiTietNgayNghiemThu = findViewById(R.id.tvChiTietNgayNghiemThu);

        tvChiTietKinhPhi = findViewById(R.id.tvChiTietKinhPhi);
        tvChiTieSoThanhVien = findViewById(R.id.tvChiTieSoThanhVien);
        tvChiTietMoTa = findViewById(R.id.tvmota);
        displayData(maDeTai);
        btnSuaDeTai = (Button) findViewById(R.id.btnSuaChiTietDeTai);

        project = new ProjectChiTietQL();
        btnSuaDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(ChiTietDTQuanLyActivity.this, SuaDeTaiActivity.class);
//                intent.putExtra("maDeTai", deTai.getId());
//                startActivity(intent);
//                displayData(maDeTai);
                SuaDeTaiDiaLog suaDeTaiDiaLog = new SuaDeTaiDiaLog();
                suaDeTaiDiaLog.setProject(project);
                suaDeTaiDiaLog.show(getSupportFragmentManager(), "SuaDeTaiDiaLog");
//                full screen


            }
        });
//        btnxoadeTai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Xử lý sự kiện khi người dùng click vào nút xóa
//
//                Toast.makeText(ChiTietDTQuanLyActivity.this, "Xóa đề tài thành công", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });

        findViewById(R.id.btnreturn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietDTQuanLyActivity.this, QuanLyDeTaiActivity.class);
                startActivity(intent);
            }
        });
    }

    void displayData(String maDeTai) {
        APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
        Call<APIResponse<ProjectChiTietQL>> call = apiService.getProjectById(maDeTai);
        call.enqueue(new retrofit2.Callback<APIResponse<ProjectChiTietQL>>() {
            @Override
            public void onResponse(Call<APIResponse<ProjectChiTietQL>> call, retrofit2.Response<APIResponse<ProjectChiTietQL>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
                        DecimalFormat decimalFormat = new DecimalFormat("#");
                        project = response.body().getResult();
                        ProjectChiTietQL project = response.body().getResult();
                        tvChiTietMaDeTai.setText(project.getId());
                        tvChiTietTenDeTai.setText(project.getName());
                        tvChiTietChuDe.setText(project.getTopic().getName());
                        tvChiTietNgayDang.setText(project.getCreateDate());
                        tvChiTietNgayMoDang.setText(project.getOpenRegDate());
                        tvChiTietNgayKetThucDang.setText(project.getCloseRegDate());
                        tvChiTietNgayBatDau.setText(project.getStartDate());
                        tvChiTietNgayKetThuc.setText(project.getEndDate());
                        tvChiTietNgayNghiemThu.setText(project.getAcceptanceDate());
                        String doubleString = decimalFormat.format(project.getEstBudget()) + " VNĐ";
                        tvChiTietKinhPhi.setText(doubleString);
                        tvChiTieSoThanhVien.setText(project.getMaxMember()+"");
                        tvChiTietMoTa.setText(project.getDescription());

                    }
                    else {
//                        Log.e("TAG", "onResponse: " + response.body().getMessage() );
                    }



                } else {
                    Log.e("TAG", "onResponse: " + response.message());
                    //Hiển thị thông tin đề tài lên giao diện
                }
            }

            @Override
            public void onFailure(Call<APIResponse<ProjectChiTietQL>> call, Throwable t) {
                //Xử lý lỗi
            }
        });
    }
    void suadetai(Project project){
        APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
        apiService.updateProject(project).enqueue(new retrofit2.Callback<APIResponse<Project>>() {
            @Override
            public void onResponse(Call<APIResponse<Project>> call, retrofit2.Response<APIResponse<Project>> response) {
                if (response.body().isSuccess()) {
                    Toast.makeText(ChiTietDTQuanLyActivity.this, "Sửa đề tài thành công", Toast.LENGTH_SHORT).show();
                    displayData(maDeTai);

                } else {
                    Toast.makeText(ChiTietDTQuanLyActivity.this, "Sửa đề tài thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Project>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}