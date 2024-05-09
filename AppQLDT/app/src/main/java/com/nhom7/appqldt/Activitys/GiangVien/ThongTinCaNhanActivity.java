package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.R;

public class ThongTinCaNhanActivity extends AppCompatActivity {

    TextView tv_CN_HoTen, tv_CN_MaSo, tv_CN_GioiTinh,tv_CN_NgaySinh,tv_CN_CCCD,tv_CN_SDT,tv_CN_DiaChi ,tv_CN_Email,tv_CN_Khoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_ca_nhan);

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
    }

    private void AnhXa(){
        tv_CN_HoTen = (TextView) findViewById(R.id.tv_CN_HoTen);
        tv_CN_MaSo = (TextView) findViewById(R.id.tv_CN_MaSo);
        tv_CN_CCCD = (TextView) findViewById(R.id.tv_CN_CCCD);
        tv_CN_DiaChi = (TextView) findViewById(R.id.tv_CN_DiaChi);
        tv_CN_Email = (TextView) findViewById(R.id.tv_CN_Email);
        tv_CN_GioiTinh = (TextView) findViewById(R.id.tv_CN_GioiTinh);
        tv_CN_Khoa = (TextView) findViewById(R.id.tv_CN_Khoa);
        tv_CN_NgaySinh = (TextView) findViewById(R.id.tv_CN_NgaySinh);
        tv_CN_SDT = (TextView) findViewById(R.id.tv_CN_SDT);
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