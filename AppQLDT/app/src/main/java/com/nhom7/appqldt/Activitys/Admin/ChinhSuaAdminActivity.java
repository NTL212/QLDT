package com.nhom7.appqldt.Activitys.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.nhom7.appqldt.Activitys.DTO.AdminDTO;
import com.nhom7.appqldt.Helpers.CheckHepler;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaAdminActivity extends AppCompatActivity {

    EditText etmaadmin, ethoten, etngaysinh, etcccd, etsdt, etemail, etdiachi, etpassword;

    Spinner spinnergioitinh;

    String username, sex, falculityCode;

    String[] genders = {"Nam", "Nữ"};

    Button btnXacNhanChinhSua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_admin);


        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });

        // Lấy username từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Hiển thị username trong TextView
        TextView tvUserName = findViewById(R.id.toolbar_username);
        tvUserName.setText(username);


        AnhXa();
        getGioiTinh();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("adCode")) {
            String value = intent.getStringExtra("adCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<AdminDTO>> call = apiService.getAdminByAdCode(value);

            call.enqueue(new Callback<APIResponse<AdminDTO>>() {
                @Override
                public void onResponse(Call<APIResponse<AdminDTO>> call, Response<APIResponse<AdminDTO>> response) {
                    if(response.isSuccessful()){
                        AdminDTO adminDTO = response.body().getResult();
                        etmaadmin.setText(adminDTO.getAdmin().getAdCode());
                        ethoten.setText(adminDTO.getAdmin().getName());
                        etngaysinh.setText(adminDTO.getAdmin().getBirthday());
                        etcccd.setText(adminDTO.getAdmin().getIdNum());
                        etsdt.setText(adminDTO.getAdmin().getPhoneNum());
                        etemail.setText(adminDTO.getAdmin().getEmail());
                        etdiachi.setText(adminDTO.getAdmin().getAddress());
                        etpassword.setText(adminDTO.getAccount().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<AdminDTO>> call, Throwable t) {

                }
            });

        }
        btnXacNhanChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChinhSuaAdminActivity.this)
                        .setTitle("Xác nhận chỉnh sửa")
                        .setMessage("Bạn có chắc chắn muốn chỉnh sửa giảng viên này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String massoadmin = etmaadmin.getText().toString().trim();
                                String username = etmaadmin.getText().toString().trim();
                                String password = etpassword.getText().toString().trim();
                                String name = ethoten.getText().toString().trim();
                                String birthday = etngaysinh.getText().toString().trim();
                                String address = etdiachi.getText().toString().trim();
                                String idNum = etcccd.getText().toString().trim();
                                if(CheckHepler.checkCCCD(idNum)){
                                    Toast.makeText(ChinhSuaAdminActivity.this, "Nhập lại cccd/cmnd 12 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                String phoneNum = etsdt.getText().toString().trim();
                                if(CheckHepler.checkPhone(phoneNum)){
                                    Toast.makeText(ChinhSuaAdminActivity.this, "Nhập lại so dien thoai 10 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                String email = etemail.getText().toString().trim();

                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.editAccount(
                                        username, password, name, birthday, address, idNum, phoneNum, email, sex, falculityCode
                                );

                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()){
                                            Toast.makeText(ChinhSuaAdminActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChinhSuaAdminActivity.this, ChiTietAdminActivity.class);
                                            startActivity(intent);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChinhSuaAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }
    private void AnhXa(){
        etmaadmin = findViewById(R.id.et_chinhsuaadmin_maadmin_Admin);
        ethoten = findViewById(R.id.et_chinhsuaadmin_ten_Admin);
        etngaysinh = findViewById(R.id.et_chinhsuaadmin_ngaysinh_Admin);
        etcccd = findViewById(R.id.et_chinhsuaadmin_CCCD_Admin);
        etsdt = findViewById(R.id.et_chinhsuaadmin_SDT_Admin);
        spinnergioitinh = findViewById(R.id.spinner_chinhsuaadmin_GioiTinh_Admin);
        etemail = findViewById(R.id.et_chinhsuaadmin_Email_Admin);
        etdiachi = findViewById(R.id.et_chinhsuaadmin_diachi_Admin);
        etpassword = findViewById(R.id.et_chinhsuaadmin_Password_Admin);
        btnXacNhanChinhSua = findViewById(R.id.btn_XacNhan_ChinhSuaAdmin_Admin);
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
    private void getGioiTinh(){

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