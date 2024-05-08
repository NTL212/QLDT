package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.R;

public class ChiTietDeTaiActivity extends AppCompatActivity {

    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietNguoiDang, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_de_tai);

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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("projectCode")) {
            String value = intent.getStringExtra("projectCode"); // Lấy dữ liệu từ Intent
            // Xử lý dữ liệu ở đây
            TextView textView = (TextView) findViewById(R.id.tvChiTietMaDeTai);
            textView.setText(value);
        }
    }
    private void AnhXa(){
        tvChiTietMaDeTai = (TextView) findViewById(R.id.tvChiTietMaDeTai);
        tvChiTietTenDeTai = (TextView) findViewById(R.id.tvChiTietTenDeTai);
        tvChiTietChuDe = (TextView) findViewById(R.id.tvChiTietChuDe);
        tvChiTietNgayDang = (TextView) findViewById(R.id.tvChiTietNgayDang);
        tvChiTietNgayKetThucDang = (TextView) findViewById(R.id.tvChiTietNgayKetThucDang);
        tvChiTietNgayBatDau = (TextView) findViewById(R.id.tvChiTietNgayBatDau);
        tvChiTietNgayKetThuc = (TextView) findViewById(R.id.tvChiTietNgayKetThuc);
        tvChiTietNgayNghiemThu = (TextView) findViewById(R.id.tvChiTietNgayNghiemThu);
        tvChiTietKinhPhi = (TextView) findViewById(R.id.tvChiTietKinhPhi);
        tvChiTieSoThanhVien = (TextView) findViewById(R.id.tvChiTieSoThanhVien);
        tvChiTietNguoiDang = (TextView) findViewById(R.id.tvChiTietNguoiDang);
        tvChiTietNgayMoDang = (TextView) findViewById(R.id.tvChiTietNgayMoDang);
        tvChiTietMoTa = (TextView) findViewById(R.id.tvChiTietMoTa);

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