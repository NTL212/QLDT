package com.nhom7.appqldt.Activitys.Admin;

import android.content.Context;
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
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.appcompat.widget.Toolbar;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietGiangVienAdminActivity extends AppCompatActivity {

    TextView tvmagiangvien, tvkhoa, tvhoten, tvngaysinh, tvcccd, tvdienthoai, tvemail, tvgioitinh, tvdiachi, tvusername, tvpassword;

    Button btnXoaGV, btnChinhSuaGV;

    String username;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_giang_vien_admin);

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
        username = sharedPreferences.getString("username", "");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);

        AnhXa();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("lecturerCode")) {
            String value = intent.getStringExtra("lecturerCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<LecturerAccount>> call = apiService.getLecturerById(value);
            call.enqueue(new Callback<APIResponse<LecturerAccount>>() {
                @Override
                public void onResponse(Call<APIResponse<LecturerAccount>> call, Response<APIResponse<LecturerAccount>> response) {
                    if (response.isSuccessful()) {
                        LecturerAccount lecturerAccount = response.body().getResult();
                        tvmagiangvien.setText(lecturerAccount.getLecturer().getLecturerCode());
                        tvkhoa.setText(lecturerAccount.getLecturer().getFaculty().getName());
                        tvhoten.setText(lecturerAccount.getLecturer().getName());
                        tvngaysinh.setText(lecturerAccount.getLecturer().getBirthday());
                        tvcccd.setText(lecturerAccount.getLecturer().getIdNum());
                        tvdienthoai.setText(lecturerAccount.getLecturer().getPhoneNum());
                        tvemail.setText(lecturerAccount.getLecturer().getEmail());
                        tvgioitinh.setText(lecturerAccount.getLecturer().getSex());
                        tvdiachi.setText(lecturerAccount.getLecturer().getAddress());
                        tvusername.setText(lecturerAccount.getAccount().getUsername());
                        tvpassword.setText(lecturerAccount.getAccount().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<LecturerAccount>> call, Throwable t) {

                }
            });
        }
        btnXoaGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChiTietGiangVienAdminActivity.this)
                        .setTitle("Xác nhận xoá")
                        .setMessage("Bạn có chắc chắn muốn xóa giảng viên này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.deleteAccount(String.valueOf(tvmagiangvien.getText()));
                                Log.d("magv",String.valueOf(tvmagiangvien.getText()));
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(ChiTietGiangVienAdminActivity.this, "Xoá giảng viên thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChiTietGiangVienAdminActivity.this, DanhSachGiangVienAdminActivity.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(ChiTietGiangVienAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChiTietGiangVienAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn "No"
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        btnChinhSuaGV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ChiTietGiangVienAdminActivity.this, ChinhSuaGiangVienAdminActivity.class);
                String lecturerCode = tvmagiangvien.getText().toString();
                Log.d("LecturerCode", lecturerCode);
                editIntent.putExtra("lecturerCode", lecturerCode);
                startActivity(editIntent);
            }
        });
    }

    private void AnhXa() {
        tvmagiangvien = (TextView) findViewById(R.id.tv_chitietgiangvien_magiangvien_Admin);
        tvkhoa = (TextView) findViewById(R.id.tv_chitietgiangvien_khoa_Admin);
        tvhoten = (TextView) findViewById(R.id.tv_chitietgiangvien_ten_Admin);
        tvngaysinh = (TextView) findViewById(R.id.tv_chitietgiangvien_ngaysinh_Admin);
        tvcccd = (TextView) findViewById(R.id.tv_chitietgiangvien_CCCD_Admin);
        tvdienthoai = (TextView) findViewById(R.id.tv_chitietgiangvien_SDT_Admin);
        tvemail = (TextView) findViewById(R.id.tv_chitietgiangvien_Email_Admin);
        tvgioitinh = (TextView) findViewById(R.id.tv_chitietgiangvien_GioiTinh_Admin);
        tvdiachi = (TextView) findViewById(R.id.tv_chitietgiangvien_diachi_Admin);
        tvusername = (TextView) findViewById(R.id.tv_chitietgiangvien_Username_Admin);
        tvpassword = (TextView) findViewById(R.id.tv_chitietgiangvien_Password_Admin);
        btnXoaGV = (Button) findViewById(R.id.btn_XoaGV_Admin);
        btnChinhSuaGV = (Button) findViewById(R.id.btn_ChinhSuaGV_Admin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean sItem = MenuHelper.ChonItem(this, item);
        if (sItem) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}