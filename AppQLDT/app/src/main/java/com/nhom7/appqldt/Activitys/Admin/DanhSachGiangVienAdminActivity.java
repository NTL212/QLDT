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
import com.nhom7.appqldt.Adapters.GiangVienAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachGiangVienAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_danhsachgiangvien);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_giang_vien_admin);

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
        loadRecyclerView();

    }
    private void loadRecyclerView(){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<LecturerAccount>>> call = apiService.getAllLecturer();
        call.enqueue(new Callback<APIResponse<List<LecturerAccount>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<LecturerAccount>>> call, Response<APIResponse<List<LecturerAccount>>> response) {
                if(response.isSuccessful()) {
                    List<LecturerAccount> lecturerAccounts = response.body().getResult();
                    GiangVienAdapter giangVienAdapter = new GiangVienAdapter(lecturerAccounts, DanhSachGiangVienAdminActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachGiangVienAdminActivity.this, LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(giangVienAdapter);

                    Log.d("giangviencount", String.valueOf(giangVienAdapter.getItemCount()));
                }else {
                    Log.e("Response Unsuccessful", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<LecturerAccount>>> call, Throwable t) {
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
        if(sItem){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}