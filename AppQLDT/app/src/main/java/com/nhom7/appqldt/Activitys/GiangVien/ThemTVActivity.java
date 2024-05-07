package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.nhom7.appqldt.R;

public class ThemTVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_tvactivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
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
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_registerProject) {
            intent = new Intent(ThemTVActivity.this, ListDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_suggestedProject) {
            intent = new Intent(ThemTVActivity.this, DeXuatDTActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_doneProject) {
            return true;
        }else if (id == R.id.action_projectMember) {
            intent = new Intent(ThemTVActivity.this, DanhSachTVActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}