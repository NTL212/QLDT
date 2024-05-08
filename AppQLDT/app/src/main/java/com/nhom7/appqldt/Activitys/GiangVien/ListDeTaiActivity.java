package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Helpers.DangNhapHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

public class ListDeTaiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DeTai> listDeTai;

    private void initializeData() {
        listDeTai = new ArrayList<>();
        listDeTai.add(new DeTai("60650", "Title 0", "Chu De 0", "Đang mở đăng ký"));
        listDeTai.add(new DeTai("60651", "Title 1", "Chu De 1", "Đang mở đăng ký"));
        listDeTai.add(new DeTai("60652", "Title 2", "Chu De 2", "Đang mở đăng ký"));
        listDeTai.add(new DeTai("60653", "Title 3", "Chu De 3", "Đang mở đăng ký"));
        listDeTai.add(new DeTai("60654", "Title 4", "Chu De 4", "Đang mở đăng ký"));
    }

    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_detais);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_de_tai);

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

        initializeData();
        AnhXa();
        loadRecyclerView();
    }

    private void loadRecyclerView() {
        DeTaiAdapter songAdapter = new DeTaiAdapter(this.listDeTai, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(songAdapter);
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