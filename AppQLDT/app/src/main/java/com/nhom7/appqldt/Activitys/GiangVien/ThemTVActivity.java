package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Adapters.ThemSinhVienAdapter;
import com.nhom7.appqldt.Helpers.DangNhapHelper;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Student;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemTVActivity extends AppCompatActivity {

    String projectCode;
    TextView tvSearch;
    RecyclerView recyclerView;
    List<Student> studentList = new ArrayList<>();
    Button btnSearch;

    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_themsinhvien);
        btnSearch = findViewById(R.id.btnSearchThanhVien);
        tvSearch = findViewById(R.id.tvSearchThanhVien);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tvactivity);

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
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(sharedPreferences.getString("username",""));

        AnhXa();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("projectCode")) {
            projectCode = intent.getStringExtra("projectCode");
        }

        loadRecyclerView();

//        tvBack.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

        btnSearch.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Call<APIResponse<List<StudentDTO>>> call = apiService.getAllStudentByKeyword(tvSearch.getText().toString().trim());
                call.enqueue(new Callback<APIResponse<List<StudentDTO>>>() {
                    @Override
                    public void onResponse(Call<APIResponse<List<StudentDTO>>> call, Response<APIResponse<List<StudentDTO>>> response) {
                        if(response.isSuccessful()){
                            if(response.body().isSuccess()){
                                List<StudentDTO> students = response.body().getResult();
                                ThemSinhVienAdapter themSinhVienAdapter = new ThemSinhVienAdapter(students, ThemTVActivity.this, projectCode);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThemTVActivity.this, LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(themSinhVienAdapter);
                            }else{
                                DialogHelper.showFailedDialog(ThemTVActivity.this, "Tìm thất bại");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<List<StudentDTO>>> call, Throwable t) {
                        DialogHelper.showFailedDialog(ThemTVActivity.this, "Không gọi được API");
                    }
                });
            }
        });

    }

    private void loadRecyclerView() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<StudentDTO>>> call = apiService.getAllStudent();
        call.enqueue(new Callback<APIResponse<List<StudentDTO>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<StudentDTO>>> call, Response<APIResponse<List<StudentDTO>>> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        List<StudentDTO> students = response.body().getResult();
                        ThemSinhVienAdapter themSinhVienAdapter = new ThemSinhVienAdapter(students, ThemTVActivity.this, projectCode);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThemTVActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(themSinhVienAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<StudentDTO>>> call, Throwable t) {

            }
        });

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
}