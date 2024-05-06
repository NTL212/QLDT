package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nhom7.appqldt.R;

public class SuaDeTaiActivity extends AppCompatActivity {
    EditText maDeTai, tenDeTai,chuDe,ngayMoDangKy, ngayKetThucDangKy, ngayNghiemThu, ngayHetHan,kinhPhiDuKien,soLuongThanhVienToiDa;
    Button btnsuaDeTai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_de_tai);
        maDeTai = (EditText) findViewById(R.id.editTextMaDeTai);
        maDeTai.setText(getIntent().getStringExtra("maDeTai"));
        maDeTai = findViewById(R.id.editTextMaDeTai);
        tenDeTai = findViewById(R.id.editTextTenDeTai);
        chuDe = findViewById(R.id.editTextChuDe);
        ngayMoDangKy = findViewById(R.id.editTextNgayMoDangKy);
        ngayKetThucDangKy = findViewById(R.id.editTextNgayKetThucDangKy);
        ngayNghiemThu = findViewById(R.id.editTextNgayNghiemThu);
        ngayHetHan = findViewById(R.id.editTextNgayHetHan);
        kinhPhiDuKien = findViewById(R.id.editTextKinhPhiDuKien);
        soLuongThanhVienToiDa = findViewById(R.id.editTextSoLuongTVToiDa);
        btnsuaDeTai = findViewById(R.id.btnSuaDeTai);

        btnsuaDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDeTai = SuaDeTaiActivity.this.maDeTai.getText().toString();
                Toast.makeText(SuaDeTaiActivity.this, "Sửa đề tài thành công"+maDeTai, Toast.LENGTH_SHORT).show();
            }
        });


    }
}