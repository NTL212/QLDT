package com.nhom7.appqldt.Activitys.Admin;

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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.Models.Student;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietHocSinhAdminActivity extends AppCompatActivity {

    TextView tvmahocsinh, tvkhoa, tvhoten, tvngaysinh, tvcccd, tvdienthoai, tvemail, tvgioitinh, tvdiachi, tvnganh, tvlop;

    Button btnXoaHS, btnChinhSuaHS;

    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoc_sinh_admin);

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
        if (intent != null && intent.hasExtra("studentId")) {
            String value = intent.getStringExtra("studentId");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<Student>> call = apiService.getStudentByid(value);

            call.enqueue(new Callback<APIResponse<Student>>() {
                @Override
                public void onResponse(Call<APIResponse<Student>> call, Response<APIResponse<Student>> response) {
                    if (response.isSuccessful()) {
                        Student student = response.body().getResult();
                        Log.d("mahs", String.valueOf(student.getStudentCode()));
                        tvmahocsinh.setText(student.getStudentCode());
                        tvhoten.setText(student.getName());
                        tvngaysinh.setText(student.getBirthday());
                        tvcccd.setText(student.getIdNum());
                        tvdienthoai.setText(student.getPhoneNum());
                        tvemail.setText(student.getEmail());
                        tvgioitinh.setText(student.getSex());
                        tvdiachi.setText(student.getAddress());
                        tvkhoa.setText(student.getClassDTO().getMajor().getFalc().getName());
                        tvnganh.setText(student.getClassDTO().getMajor().getName());
                        tvlop.setText(student.getClassDTO().getName());
                    } else {
                        Log.d("Response unsuccesful", response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<Student>> call, Throwable t) {

                }
            });

        }
        btnXoaHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChiTietHocSinhAdminActivity.this)
                        .setTitle("Xác nhận xoá")
                        .setMessage("Bạn có chắc chắn muốn xóa giảng viên này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.deleteStudent(String.valueOf(tvmahocsinh.getText()));
                                Log.d("mahs",String.valueOf(tvmahocsinh.getText()));
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()){
                                            Log.d("Xoá hs","Xoá hs thành công");
                                            Toast.makeText(ChiTietHocSinhAdminActivity.this, "Xoá giảng viên thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChiTietHocSinhAdminActivity.this, DanhSachHocSinhAdminActivity.class);
                                            startActivity(intent);
                                        }else {
                                            Toast.makeText(ChiTietHocSinhAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChiTietHocSinhAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn "No"
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
        btnChinhSuaHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ChiTietHocSinhAdminActivity.this, ChinhSuaHocSinhAdminActivity.class);
                String studentCode = tvmahocsinh.getText().toString();
                Log.d("StudentCode", studentCode);
                editIntent.putExtra("studentCode", studentCode);
                startActivity(editIntent);
            }
        });

    }
    private void AnhXa () {
        tvmahocsinh = (TextView) findViewById(R.id.tv_chitiethocsinh_mahocsinh_Admin);
        tvkhoa = (TextView) findViewById(R.id.tv_chitiethocsinh_khoa_Admin);
        tvhoten = (TextView) findViewById(R.id.tv_chitiethocsinh_ten_Admin);
        tvngaysinh = (TextView) findViewById(R.id.tv_chitiethocsinh_ngaysinh_Admin);
        tvcccd = (TextView) findViewById(R.id.tv_chitiethocsinh_CCCD_Admin);
        tvdienthoai = (TextView) findViewById(R.id.tv_chitiethocsinh_SDT_Admin);
        tvemail = (TextView) findViewById(R.id.tv_chitiethocsinh_Email_Admin);
        tvgioitinh = (TextView) findViewById(R.id.tv_chitiethocsinh_GioiTinh_Admin);
        tvdiachi = (TextView) findViewById(R.id.tv_chitiethocsinh_diachi_Admin);
        tvnganh = (TextView) findViewById(R.id.tv_chitiethocsinh_nganh_Admin);
        tvlop = (TextView) findViewById(R.id.tv_chitiethocsinh_lop_Admin);
        btnXoaHS = (Button) findViewById(R.id.btn_XoaHS_Admin);
        btnChinhSuaHS = (Button) findViewById(R.id.btn_ChinhSuaHS_Admin);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        boolean sItem = MenuHelper.ChonItem(this, item);
        if (sItem) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}