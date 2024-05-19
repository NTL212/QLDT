package com.nhom7.appqldt.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.DeXuatDTActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.Activitys.QuanLy.ListChuDeActivity;
import com.nhom7.appqldt.Activitys.QuanLy.QuanLyDeTaiActivity;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    CheckBox checkBoxRemember;
    Button buttonControl;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
//Thông tin sẽ được luu trữ vào bộ nhớ của ứng dụng
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        mapping();
//        if (sharedPreferences.getBoolean("isLogin", false)) {
//            Intent intent = new Intent(this, ListDeTaiActivity.class);
//            startActivity(intent);
//        }
//Lấy giá trị được lưu giữ ra
        if (sharedPreferences.getBoolean("checked", false)) {
            editTextUsername.setText((CharSequence) sharedPreferences.getString("username", ""));
            editTextPassword.setText((CharSequence) sharedPreferences.getString("password", ""));
        }
        checkBoxRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        buttonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void mapping() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        buttonControl = (Button) findViewById(R.id.buttonControl);
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<Account>> call = apiService.login(username, password);
        call.enqueue(new Callback<APIResponse<Account>>() {
            @Override
            public void onResponse(Call<APIResponse<Account>> call, Response<APIResponse<Account>> response) {
                if (response.isSuccessful()) {
                    APIResponse<Account> loginResponse = response.body();
                    if (loginResponse.isSuccess()) {
                        Intent intent;
//                    Nếu có checked thì mới lưu thông tin vào
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        if (checkBoxRemember.isChecked()) {
                            editor.putString("username", username);
                            editor.putString("password", password);
                            editor.putBoolean("checked", checkBoxRemember.isChecked());
                            editor.putBoolean("isLogin", true);
                            editor.commit();
                        } else {
                            editor.remove("checked");
                            editor.commit();
                        }
                        if (loginResponse.getResult().getRole().contains("ROLE_LECT")) {
                            intent = new Intent(DangNhapActivity.this, ListDeTaiActivity.class);
                            startActivity(intent);
                        } else if (loginResponse.getResult().getRole().contains("ROLE_MGT_STAFF")) {
                            intent = new Intent(DangNhapActivity.this, QuanLyDeTaiActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Account>> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}