package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom7.appqldt.R;

public class ChiTietDTPheDuyetActivity extends AppCompatActivity {
    Button btnXemThanhVien, btnPheDuyet, btnKhongPheDuyet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_dtphe_duyet);
        btnXemThanhVien = findViewById(R.id.btnXemThanhVien);
        btnPheDuyet = findViewById(R.id.btnPheDuyet);
        btnKhongPheDuyet = findViewById(R.id.btnKhongPheDuyet);
        btnXemThanhVien.setOnClickListener(v -> {
            XemThanhVienDeTaiDialogFragment dialogFragment = new XemThanhVienDeTaiDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "XemThanhVienDeTaiDialogFragment");
        });

    }
}