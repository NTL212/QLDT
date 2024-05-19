package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
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
import com.nhom7.appqldt.Activitys.DangNhapActivity;
import com.nhom7.appqldt.Adapters.ThongBaoAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongBaoDaGui_QLActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Notification> thongBaoList;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao_da_gui_ql);

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
        Call<APIResponse<List<Notification>>> call = apiService.getAllSendedMessageLecture(username);
        call.enqueue(new Callback<APIResponse<List<Notification>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Notification>>> call, Response<APIResponse<List<Notification>>> response) {
                if(response.isSuccessful()){
                    thongBaoList = (List<Notification>) response.body().getResult();
                    ThongBaoAdapter thongBaoAdapter = new ThongBaoAdapter(thongBaoList, ThongBaoDaGui_QLActivity.this, true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ThongBaoDaGui_QLActivity.this, LinearLayoutManager.VERTICAL, false);
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
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_sendNotification) {
            intent = new Intent(this, GuiThongBaoActivity.class);
            startActivity(intent);
            return true;
        }  else if (id == R.id.action_manageProject) {
            intent = new Intent(this, QuanLyDeTaiActivity.class);
            startActivity(intent);
            return true;
        }else if (id ==R.id.action_receivedNotification){
            intent = new Intent(this, ThongBaoNhan_QLActivity.class);
            startActivity(intent);
            return true;
        }else if (id ==R.id.action_logout){
            intent = new Intent(this, DangNhapActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_projectTopic) {
            intent = new Intent(this, ListChuDeActivity.class);
            startActivity(intent);
            return true;
        } else if (id==R.id.action_approveProject) {
            intent = new Intent(this, PheDuyetDeTaiActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}