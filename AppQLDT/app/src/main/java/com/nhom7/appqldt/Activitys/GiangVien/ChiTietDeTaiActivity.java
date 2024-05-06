package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.nhom7.appqldt.R;

public class ChiTietDeTaiActivity extends AppCompatActivity {


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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("projectCode")) {
            String value = intent.getStringExtra("projectCode"); // Lấy dữ liệu từ Intent
            // Xử lý dữ liệu ở đây
            TextView textView = (TextView) findViewById(R.id.tvChiTietMaDeTai);
            textView.setText(value);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_registerProject) {
            intent = new Intent(ChiTietDeTaiActivity.this, ListDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_suggestedProject) {
            intent = new Intent(ChiTietDeTaiActivity.this, DeXuatDTActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_doneProject) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}