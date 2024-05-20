package com.nhom7.appqldt.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.Admin.DanhSachQuanLyAdminActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.DeXuatDTActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ThongTinCaNhanActivity;
import com.nhom7.appqldt.Activitys.QuanLy.ListChuDeActivity;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Video1Model;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhapActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private Button buttonFetchData;
    private List<Video1Model> videoList = new ArrayList<>();
    EditText editTextUsername, editTextPassword;
    CheckBox checkBoxRemember;
    Button buttonControl;

    SharedPreferences sharedPreferences;
    private void getDuLieu() {
        mDatabase = FirebaseDatabase.getInstance().getReference("videos");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                videoList.clear();
                for (DataSnapshot videoSnapshot : dataSnapshot.getChildren()) {
                    Video1Model video = videoSnapshot.getValue(Video1Model.class);
                    videoList.add(video);
                }
                displayData();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("Firebase", "Error: " + databaseError.getMessage());
            }
        });
    }
    private void displayData() {
        StringBuilder data = new StringBuilder();
        for (Video1Model video : videoList) {
            data.append("Title: ").append(video.getTitle()).append("\n")
                    .append("Description: ").append(video.getDesc()).append("\n")
                    .append("URL: ").append(video.getUrl()).append("\n\n");
        }
        Log.d("saddsđ",data.toString());
    }
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

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng nhập");
        progressDialog.setCancelable(false);
        progressDialog.show();

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

                        progressDialog.dismiss();

                        if (loginResponse.getResult().getRole().contains("ROLE_LECT")) {
                            intent = new Intent(DangNhapActivity.this, ListDeTaiActivity.class);
                            startActivity(intent);
                        } else if (loginResponse.getResult().getRole().contains("ROLE_MGT_STAFF")) {
                            intent = new Intent(DangNhapActivity.this, ListChuDeActivity.class);
                            startActivity(intent);
                        }else if (loginResponse.getResult().getRole().contains("ROLE_ADMIN")) {
                            intent = new Intent(DangNhapActivity.this, DanhSachQuanLyAdminActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(DangNhapActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                } else {
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }


            }

            @Override
            public void onFailure(Call<APIResponse<Account>> call, Throwable t) {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}