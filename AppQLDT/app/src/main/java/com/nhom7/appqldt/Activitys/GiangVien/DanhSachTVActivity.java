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
import com.nhom7.appqldt.Adapters.ThanhVienAdapter;
import com.nhom7.appqldt.Helpers.DangNhapHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DanhSachTVActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ThanhVien> listTV;

    private void initializeData() {
        listTV = new ArrayList<>();
        String dateString = "01/01/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        listTV.add(new ThanhVien(1, "Nguyen Van A", "2011", date, "1A","CNTT"));
        listTV.add(new ThanhVien(2, "Tran Thi B", "2012", date, "1A","CNTT"));
        listTV.add(new ThanhVien(3, "Le Chi D", "2013", date, "1A","CNTT"));
    }

    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_detais);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tvactivity);

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
        ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(this.listTV, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thanhVienAdapter);
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