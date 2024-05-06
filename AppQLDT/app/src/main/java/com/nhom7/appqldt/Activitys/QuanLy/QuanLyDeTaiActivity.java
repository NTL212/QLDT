package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.R;


import java.util.ArrayList;
import java.util.List;

public class QuanLyDeTaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DeTai> listDeTai;
    Button btnThemDeTai;
    DeTaiAdapter deTaiAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_de_tai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });
        anhxa();
        initializeData();
        recyclerView = findViewById(R.id.recycler_view_detais);
        deTaiAdapter = new DeTaiAdapter(this, listDeTai);
        recyclerView.setAdapter(deTaiAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnThemDeTai.setOnClickListener(v -> {
            ThemDeTaiDialogFragment themDeTaiDialogFragment = new ThemDeTaiDialogFragment();
            themDeTaiDialogFragment.show(getSupportFragmentManager(), "Them De Tai");

        });


    }

    void anhxa(){
        btnThemDeTai = findViewById(R.id.btnThemDeTai);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }
    private void initializeData() {
        listDeTai = new ArrayList<>();
        listDeTai.add(new DeTai("60650", "Title 0", "Chu De 0", "Đang mở đăng ký"));
        listDeTai.add(new DeTai("60651", "Title 1", "Chu De 1", "Đang mở đăng ký"));

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
        } else if (id == R.id.action_projectTopic) {
            intent = new Intent(this, ListChuDeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}