package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Adapters.ChuDeAdapter;
import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.DeTai;
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
        chuDeList = new ArrayList<>();
        chuDeList.add(new ChuDe(1, "Chủ đề 1", 10, true));
        chuDeList.add(new ChuDe(2, "Chủ đề 2", 10, false));
        chuDeList.add(new ChuDe(3, "Chủ đề 3", 10, true));

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
        chuDeList.add(new ChuDe( 1, tenChuDe, 0, false));
        chuDeAdapter.notifyDataSetChanged();
    }
}