package com.nhom7.appqldt.Activitys.QuanLy;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.R;

public class ChiTietDTQuanLyActivity extends AppCompatActivity {
    Button btnSuaDeTai, btnxoadeTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dtquan_ly);
        DeTai deTai = new DeTai();
        deTai.setMaDeTai(getIntent().getStringExtra("maDeTai"));
        TextView textView = (TextView) findViewById(R.id.tvChiTietMaDeTai);
        textView.setText(deTai.getMaDeTai());
        btnSuaDeTai = (Button) findViewById(R.id.btnSuaChiTietDeTai);
        btnxoadeTai = (Button) findViewById(R.id.btnXoaDeTai);


        btnSuaDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý sự kiện khi người dùng click vào nút sửa
                //Ở đây, bạn có thể chuyển sang màn hình sửa thông tin đề tài

                Intent intent = new Intent(ChiTietDTQuanLyActivity.this, SuaDeTaiActivity.class);
                intent.putExtra("maDeTai", deTai.getMaDeTai());
                startActivity(intent);
            }
        });
        btnxoadeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Xử lý sự kiện khi người dùng click vào nút xóa

                Toast.makeText(ChiTietDTQuanLyActivity.this, "Xóa đề tài thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}