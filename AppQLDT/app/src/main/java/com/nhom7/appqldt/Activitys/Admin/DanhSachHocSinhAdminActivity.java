package com.nhom7.appqldt.Activitys.Admin;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Adapters.StudentAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachHocSinhAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    private void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_danhsachhocsinh);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hoc_sinh_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onOptionsItemSelected(item);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(sharedPreferences.getString("username", ""));

        AnhXa();
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<StudentDTO>>> call = apiService.getAllStudent();
        call.enqueue(new Callback<APIResponse<List<StudentDTO>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<StudentDTO>>> call, Response<APIResponse<List<StudentDTO>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult() != null) {
                        List<StudentDTO> studentDTOS = response.body().getResult();
                        StudentAdapter studentAdapter = new StudentAdapter(studentDTOS, DanhSachHocSinhAdminActivity.this);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachHocSinhAdminActivity.this, LinearLayoutManager.VERTICAL, false);

                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(studentAdapter);

                        Log.d("Sinh viên count", String.valueOf(studentAdapter.getItemCount()));
                        Log.d("test", String.valueOf(response.body().getResult().size()));
                    }else {
                        Log.d("reponse lỗi", response.body().getMessage());
                    }
                } else {
                    Log.e("Response Unsuccessful", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<StudentDTO>>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
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