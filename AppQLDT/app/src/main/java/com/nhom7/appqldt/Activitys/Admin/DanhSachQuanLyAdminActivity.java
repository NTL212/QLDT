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
import com.nhom7.appqldt.Adapters.QuanLyAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.ManagerAccount;
import com.nhom7.appqldt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachQuanLyAdminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_danhsachquanly);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_quan_ly_admin);

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
        tvUserName.setText(sharedPreferences.getString("username",""));

        AnhXa();
        loadRecyclerView();

    }
    public void loadRecyclerView(){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<ManagerAccount>>> call = apiService.getAllManager();
        call.enqueue(new Callback<APIResponse<List<ManagerAccount>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<ManagerAccount>>> call, Response<APIResponse<List<ManagerAccount>>> response) {
                if(response.isSuccessful()){
                    List<ManagerAccount> managerAccounts = response.body().getResult();
                    QuanLyAdapter quanLyAdapter = new QuanLyAdapter(managerAccounts, DanhSachQuanLyAdminActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachQuanLyAdminActivity.this, LinearLayoutManager.VERTICAL, false);

                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(quanLyAdapter);
                    Log.d("Manager Count", String.valueOf(quanLyAdapter.getItemCount()));
                }else {
                    Log.e("Response Unsuccessful", response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<ManagerAccount>>> call, Throwable t) {
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