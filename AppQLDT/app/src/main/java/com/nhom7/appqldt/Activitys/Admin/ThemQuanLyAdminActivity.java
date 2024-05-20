package com.nhom7.appqldt.Activitys.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.CheckHepler;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemQuanLyAdminActivity extends AppCompatActivity {

    EditText etmaql, ethoten, etngaysinh, etcccd, etsdt, etemail, etdiachi, etpassword;

    Spinner spinnergioitinh;

    String sex, username;

    Button btnThemQL;

    String[] genders = {"Nam", "Nữ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_quan_ly_admin);

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
        getGioiTinh();

        btnThemQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ThemQuanLyAdminActivity.this)
                        .setTitle("Xác nhận thêm")
                        .setMessage("Bạn có chắc chắn muốn thêm quản lý này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String empCode = etmaql.getText().toString().trim();
                                String username = etmaql.getText().toString().trim();
                                String password = etpassword.getText().toString().trim();
                                String name = ethoten.getText().toString().trim();
                                String birthday = etngaysinh.getText().toString().trim();
                                String address = etdiachi.getText().toString().trim();
                                String idNum = etcccd.getText().toString().trim();
                                String phoneNum = etsdt.getText().toString().trim();
                                if(CheckHepler.checkCCCD(idNum)){
                                    Toast.makeText(ThemQuanLyAdminActivity.this, "Nhập lại cccd/cmnd 12 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(CheckHepler.checkPhone(phoneNum)){
                                    Toast.makeText(ThemQuanLyAdminActivity.this, "Nhập lại so dien thoai 10 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                String email = etemail.getText().toString().trim();
                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.insertManager(
                                        empCode, username, password, name, birthday, address, idNum, phoneNum, email, sex
                                        );
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()) {
                                            if (response.body().isSuccess()) {
                                                Log.d("test", response.body().getMessage());
                                                Toast.makeText(ThemQuanLyAdminActivity.this, "Thêm quản lý thành công", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(ThemQuanLyAdminActivity.this, DanhSachQuanLyAdminActivity.class);
                                                startActivity(intent);

                                            } else {
                                                Toast.makeText(ThemQuanLyAdminActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(ThemQuanLyAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ThemQuanLyAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }


                        })
                        .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn "No"
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();;;;
            }
        });


    }

    private void AnhXa() {
        etmaql = findViewById(R.id.editText_themquanly_maquanly_Admin);
        ethoten = findViewById(R.id.editText_themquanly_ten_Admin);
        etngaysinh = findViewById(R.id.editText_themquanly_NgaySinh_Admin);
        etcccd = findViewById(R.id.editText_themquanly_CCCD_Admin);
        etsdt = findViewById(R.id.editText_themquanly_SDT_Admin);
        spinnergioitinh = findViewById(R.id.spinner_themquanly_GioiTinh_Admin);
        etemail = findViewById(R.id.editText_themquanly_Email_Admin);
        etdiachi = findViewById(R.id.editText_themquanly_DiaChi_Admin);
        etpassword = findViewById(R.id.editText_themquanly_Password_Admin);
        btnThemQL = findViewById(R.id.btn_ThemQL_Admin);
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

    private void getGioiTinh() {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergioitinh.setAdapter(adapter);

        spinnergioitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                sex = selectedGender;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String selectedGender = parent.getItemAtPosition(0).toString();
            }
        });
    }
}