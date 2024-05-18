package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Adapters.ThongBaoAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.Models.ThongBao;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongBaoNhanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Notification> thongBaoList;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_nhan);

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
        username = sharedPreferences.getString("username","");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);


        AnhXa();
        loadRecyclerView();

    }
    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_thongbao);
    }
    private void loadRecyclerView() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Notification>>> call = apiService.getAllReceiveMessageLecture(username);
        call.enqueue(new Callback<APIResponse<List<Notification>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Notification>>> call, Response<APIResponse<List<Notification>>> response) {
                if(response.isSuccessful()){
                    thongBaoList = (List<Notification>) response.body().getResult();
                    ThongBaoAdapter thongBaoAdapter = new ThongBaoAdapter(thongBaoList, ThongBaoNhanActivity.this, false);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThongBaoNhanActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(thongBaoAdapter);
                }
                else{
                    Log.d("API", "List tb rong");
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Notification>>> call, Throwable t) {
                Log.d("API", "Failed call");
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