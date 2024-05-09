package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Adapters.ThongBaoAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.ThongBao;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

public class ThongBaoNhanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ThongBao> thongBaoList;
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
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(sharedPreferences.getString("username",""));


        initializeData();
        AnhXa();
        loadRecyclerView();

    }
    private void initializeData() {
        thongBaoList = new ArrayList<>();
        thongBaoList.add(new ThongBao("Nhận xét về đề tài", "Nguyễn Văn A", "01/01/2024", "Làm tốt lắm"));
        thongBaoList.add(new ThongBao("Phê duyệt đề tài", "Nguyễn Văn B", "05/01/2024", "Đã duyệt"));

    }
    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_thongbao);
    }
    private void loadRecyclerView() {
        ThongBaoAdapter thongBaoAdapter = new ThongBaoAdapter(this.thongBaoList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thongBaoAdapter);
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