package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Registration;
import com.nhom7.appqldt.R;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDeTaiActivity extends AppCompatActivity {

    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietNguoiDang, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa;
    String username;
    Button btnChiTietDangKy, btnChiTietHuyDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_de_tai);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//Lấy giá trị được lưu giữ ra
        username = sharedPreferences.getString("username","");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);

        //Ánh xạ
        AnhXa();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("projectCode")) {
            String value = intent.getStringExtra("projectCode"); // Lấy dữ liệu từ Intent
            // Xử lý dữ liệu ở đây
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<Project>> call = apiService.getProjectDetailForLecturer(value);
            call.enqueue(new Callback<APIResponse<Project>>() {
                @Override
                public void onResponse(Call<APIResponse<Project>> call, Response<APIResponse<Project>> response) {
                    if(response.isSuccessful()){
                        DecimalFormat decimalFormat = new DecimalFormat("#");
                        Project project = response.body().getResult();
                        tvChiTietMaDeTai.setText(project.getProjectCode());
                        tvChiTietTenDeTai.setText(project.getName());
                        tvChiTietChuDe.setText(project.getTopic().getName());
                        tvChiTietNgayDang.setText(project.getCreateDate());
                        tvChiTietNgayKetThucDang.setText(project.getCloseRegDate());
                        tvChiTietNgayBatDau.setText(project.getStartDate());
                        tvChiTietNgayKetThuc.setText(project.getEndDate());
                        tvChiTietNgayNghiemThu.setText(project.getAcceptanceDate());
                        tvChiTietKinhPhi.setText(decimalFormat.format(project.getEstBudget()) +" VNĐ");
                        tvChiTieSoThanhVien.setText(String.valueOf(project.getMaxMember()));
                        if(project.getLecturer() != null){
                            tvChiTietNguoiDang.setText(project.getLecturer().getName());
                        }
                        else {
                            tvChiTietNguoiDang.setText("Chưa có chủ nhiệm");
                        }
                        tvChiTietNgayMoDang.setText(project.getOpenRegDate());
                        tvChiTietMoTa.setText(project.getDescription());
                        btnChiTietHuyDangKy.setVisibility(View.GONE);
                        btnChiTietDangKy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DangKyDeTai(username, project.getProjectCode());
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<Project>> call, Throwable t) {

                }
            });
        }


    }

    private void AnhXa(){
        tvChiTietMaDeTai = (TextView) findViewById(R.id.tvChiTietMaDeTai);
        tvChiTietTenDeTai = (TextView) findViewById(R.id.tvChiTietTenDeTai);
        tvChiTietChuDe = (TextView) findViewById(R.id.tvChiTietChuDe);
        tvChiTietNgayDang = (TextView) findViewById(R.id.tvChiTietNgayDang);
        tvChiTietNgayKetThucDang = (TextView) findViewById(R.id.tvChiTietNgayKetThucDang);
        tvChiTietNgayBatDau = (TextView) findViewById(R.id.tvChiTietNgayBatDau);
        tvChiTietNgayKetThuc = (TextView) findViewById(R.id.tvChiTietNgayKetThuc);
        tvChiTietNgayNghiemThu = (TextView) findViewById(R.id.tvChiTietNgayNghiemThu);
        tvChiTietKinhPhi = (TextView) findViewById(R.id.tvChiTietKinhPhi);
        tvChiTieSoThanhVien = (TextView) findViewById(R.id.tvChiTieSoThanhVien);
        tvChiTietNguoiDang = (TextView) findViewById(R.id.tvChiTietNguoiDang);
        tvChiTietNgayMoDang = (TextView) findViewById(R.id.tvChiTietNgayMoDang);
        tvChiTietMoTa = (TextView) findViewById(R.id.tvChiTietMoTa);
        btnChiTietDangKy = (Button) findViewById(R.id.btnChiTietDangKy);
        btnChiTietHuyDangKy = (Button) findViewById(R.id.btnChiTietHuyDangKy);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean sItem = MenuHelper.ChonItem(this, item);
        if(sItem){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void DangKyDeTai(String lectCode, String projectCode){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<Registration>> call = apiService.regisProjectLecture(lectCode, projectCode);

        call.enqueue(new Callback<APIResponse<Registration>>() {
            @Override
            public void onResponse(Call<APIResponse<Registration>> call, Response<APIResponse<Registration>> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        DialogHelper.showDialog(ChiTietDeTaiActivity.this, // Context của Activity hiện tại
                                "Thông báo", // Tiêu đề của dialog
                                "Đăng ký đề tài "+ projectCode+" thành công", // Nội dung của dialog
                                "OK", // Text của nút Positive
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Xử lý sự kiện khi người dùng nhấn nút Positive
                                        dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                        Intent intent = new Intent(ChiTietDeTaiActivity.this, ListDeTaiCuaToiActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }else {
                        DialogHelper.showDialog(ChiTietDeTaiActivity.this, // Context của Activity hiện tại
                                "Thông báo", // Tiêu đề của dialog
                                "Đăng ký đề tài "+ projectCode+" thất bại", // Nội dung của dialog
                                "OK", // Text của nút Positive
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Xử lý sự kiện khi người dùng nhấn nút Positive
                                        dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                    }
                                });
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Registration>> call, Throwable t) {
                Log.d("API Lecture", "Fail call");
            }
        });
    }
}