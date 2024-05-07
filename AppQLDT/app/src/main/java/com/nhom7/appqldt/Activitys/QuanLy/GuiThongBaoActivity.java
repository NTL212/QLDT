package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom7.appqldt.R;

public class GuiThongBaoActivity extends AppCompatActivity {
    EditText nguoigui, nguoinhan, tieude, noidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_thong_bao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });
        nguoigui=findViewById(R.id.edtNguoiGui);
        nguoinhan=findViewById(R.id.edtNguoiNhan);
        tieude=findViewById(R.id.edtTieuDe);
        noidung=findViewById(R.id.edtNoiDung);

        findViewById(R.id.btnGui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gửi thông báo
                String nguoiGui = nguoigui.getText().toString();
                String nguoiNhan = nguoinhan.getText().toString();
                String tieuDe = tieude.getText().toString();
                String noiDung = noidung.getText().toString();




                
                //Gửi thông báo
                Toast.makeText(GuiThongBaoActivity.this, "Thông báo đã được gửi", Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_projectTopic) {
            intent = new Intent(this,ListChuDeActivity.class);
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

}