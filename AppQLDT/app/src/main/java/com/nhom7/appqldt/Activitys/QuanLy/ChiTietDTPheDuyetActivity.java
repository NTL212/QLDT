package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.MessageResponse;
import com.nhom7.appqldt.Models.ProjectChiTietQL;
import com.nhom7.appqldt.R;

import java.text.DecimalFormat;

import retrofit2.Call;

public class ChiTietDTPheDuyetActivity extends AppCompatActivity {
    Button btnXemThanhVien, btnPheDuyet, btnKhongPheDuyet;
    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang, tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa;
    String maDeTai;
    TextView tvLecture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_dtphe_duyet);
        btnXemThanhVien = findViewById(R.id.btnXemThanhVien);
        btnPheDuyet = findViewById(R.id.btnPheDuyet);
        btnKhongPheDuyet = findViewById(R.id.btnKhongPheDuyet);
        btnXemThanhVien.setOnClickListener(v -> {
            XemThanhVienDeTaiDialogFragment dialogFragment = new XemThanhVienDeTaiDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "XemThanhVienDeTaiDialogFragment");
        });
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
        tvLecture = findViewById(R.id.tvLecture);
//        tvChiTietMoTa = findViewById(R.id.tvChiTietMoTa);
//        tvLecture = findViewById(R.id.tvLecture);
        displayData(maDeTai);
        String LecturerCode=getIntent().getStringExtra("LecturerCode");
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String managerCode = sharedPreferences.getString("username","");

//Lấy giá trị được lưu giữ ra
        btnPheDuyet.setOnClickListener(v ->{
            APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
//            Log.e("TAG", "onResponse: " + managerCode+" "+LecturerCode+" "+maDeTai);
            Call<APIResponse<MessageResponse>> call = apiService.approveProject(managerCode,LecturerCode,maDeTai);
            call.enqueue(new retrofit2.Callback<APIResponse<MessageResponse>>() {
                @Override
                public void onResponse(Call<APIResponse<MessageResponse>> call, retrofit2.Response<APIResponse<MessageResponse>> response) {
                    Log.e("TAG", "onResponse: " + response.body() );
                    if (response.isSuccessful()) {
                        if (response.body().isSuccess()) {
                            Log.e("TAG", "onResponse: " + response.body().getResult() );
                            Toast.makeText(ChiTietDTPheDuyetActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Log.e("TAG", "onResponse: " + response.body().getMessage() );
                        }
                    } else {
                        Log.e("TAG", "onResponse: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<MessageResponse>> call, Throwable t) {
                    //Xử lý lỗi
                }
            });

        });
        btnKhongPheDuyet.setOnClickListener(v ->{
            APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
//            Log.e("TAG", "onResponse: " + managerCode+" "+LecturerCode+" "+maDeTai);
            Call<APIResponse<MessageResponse>> call = apiService.disagreeProject(managerCode,LecturerCode,maDeTai);
            call.enqueue(new retrofit2.Callback<APIResponse<MessageResponse>>() {
                @Override
                public void onResponse(Call<APIResponse<MessageResponse>> call, retrofit2.Response<APIResponse<MessageResponse>> response) {
//                    Log.e("TAG", "onResponse: " + response.body() );
                    if (response.isSuccessful()) {
                        if (response.body().isSuccess()) {
//                            Log.e("TAG", "onResponse: " + response.body().getResult() );
                            Toast.makeText(ChiTietDTPheDuyetActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else {
                            Log.e("TAG", "onResponse: " + response.body().getMessage() );
                        }
                    } else {
                        Log.e("TAG", "onResponse: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<MessageResponse>> call, Throwable t) {
                    //Xử lý lỗi
                }
            });
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
                        ProjectChiTietQL project = response.body().getResult();
                        tvChiTietMaDeTai.setText(project.getId());
                        tvChiTietTenDeTai.setText(project.getTopic().getName());
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
//                        tvChiTietMoTa.setText(project.getDescription());
                        String nameLec = getIntent().getStringExtra("nameLec");
                        if(nameLec!=null){
                            tvLecture.setText(nameLec);
                        }else {
                            tvLecture.setText("Chưa có giảng viên chủ nhiệm");
                        }

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
}