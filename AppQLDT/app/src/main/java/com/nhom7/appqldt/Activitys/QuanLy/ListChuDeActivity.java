package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Adapters.ChuDeAdapter;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

public class ListChuDeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
List<ChuDe> chuDeList;
ChuDeAdapter chuDeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chu_de);
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
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_title2);
        tvUserName.setText(sharedPreferences.getString("username",""));
        chuDeList = new ArrayList<>();
//        chuDeList.add(new ChuDe(1, "Chủ đề 1", 10, true));
//        chuDeList.add(new ChuDe(2, "Chủ đề 2", 10, false));
//        chuDeList.add(new ChuDe(3, "Chủ đề 3", 10, true));
        showListChuDe();

        recyclerView = findViewById(R.id.recycler_view_chudes);
        chuDeAdapter = new ChuDeAdapter(this, chuDeList);
        recyclerView.setAdapter(chuDeAdapter);
        chuDeAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        findViewById(R.id.btn_add_chude).setOnClickListener(v -> {
            ThemChuDeDialogFragment themChuDeDialogFragment = new ThemChuDeDialogFragment();
            themChuDeDialogFragment.show(getSupportFragmentManager(), "Them Chu De");
        });

    }
    List<Topic> topicList = new ArrayList<>();

    void showListChuDe(){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTopic().enqueue(new retrofit2.Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<List<Topic>>> call, retrofit2.Response<APIResponse<List<Topic>>> response) {
                if (response.body().isSuccess()) {
                    Log.e("TAG", "onResponse: " + response.body().getResult());
                    topicList = response.body().getResult();
                    for (Topic topic : topicList) {
                        chuDeList.add(new ChuDe(1, topic.getName(), 0, topic.isEnabled()));
                    }
                    chuDeAdapter.notifyDataSetChanged();
                    }
            }
            @Override
            public void onFailure(retrofit2.Call<APIResponse<List<Topic>>> call, Throwable t) {
                t.printStackTrace();
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
        } else if (id == R.id.action_approveProject) {
            intent = new Intent(this, PheDuyetDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_manageProject) {
            intent = new Intent(this, QuanLyDeTaiActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void themChuDe(String maSo, String tenChuDe) {
        chuDeList.add(new ChuDe( chuDeList.size() + 1, tenChuDe, 0, true));
Toast.makeText(this, maSo, Toast.LENGTH_SHORT).show();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Topic topic = new Topic(maSo, tenChuDe, true);
        apiService.insertTopic(topic).enqueue(new retrofit2.Callback<APIResponse<Topic>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<Topic>> call, retrofit2.Response<APIResponse<Topic>> response) {
                if (response.body() != null) {
                    Log.e("TAG", "onResponse: " + response.body().getResult());
                    Log.e("TAG", "onResponse: " + response.body().getMessage());
                    Log.e("TAG", "onResponse: " + response.body().getStatusCode());
                    Log.e("TAG", "onResponse: " + response.body().isSuccess());

//                    Toast.makeText(ListChuDeActivity.this, "Thêm chủ đề thành công", Toast.LENGTH_SHORT).show();
                    if (response.body().isSuccess()) {
                        Log.e("TAG", "onResponse: " + response.body().getStatusCode());
                    }
                }
                else {
                    Toast.makeText(ListChuDeActivity.this, "Thêm chủ đề thất bại", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(retrofit2.Call<APIResponse<Topic>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    public void loadListChuDe(){

    }
}