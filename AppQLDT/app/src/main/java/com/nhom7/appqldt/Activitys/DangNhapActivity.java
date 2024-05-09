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

import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.DeXuatDTActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.R;

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
        if (sharedPreferences.getBoolean("isLogin", false)){
            Intent intent = new Intent(this, ListDeTaiActivity.class);
            startActivity(intent);
        }
//Lấy giá trị được lưu giữ ra
        if(sharedPreferences.getBoolean("checked", false)){
            editTextUsername.setText((CharSequence) sharedPreferences.getString("username", ""));
            editTextPassword.setText((CharSequence) sharedPreferences.getString("password", ""));
        }
        checkBoxRemember.setChecked(sharedPreferences.getBoolean("checked", false));

        buttonControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (username.equals("gv") & password.equals("123")) {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
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
                    Intent intent = new Intent(DangNhapActivity.this, ListDeTaiActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void mapping() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        buttonControl = (Button) findViewById(R.id.buttonControl);
    }
}