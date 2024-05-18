package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SuaDeTaiActivity extends AppCompatActivity {
    EditText maDeTai, tenDeTai,ngayMoDangKy, ngayKetThucDangKy, ngayNghiemThu, editTextNgayBatDau,ngayHetHan,kinhPhiDuKien,soLuongThanhVienToiDa;
    Button btnsuaDeTai;
    Spinner spnChude;
    List<Topic> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_de_tai);
        loadintoSpinChude();
        maDeTai = (EditText) findViewById(R.id.editTextMaDeTai);
        maDeTai.setText(getIntent().getStringExtra("maDeTai"));
        maDeTai = findViewById(R.id.editTextMaDeTai);
        tenDeTai = findViewById(R.id.editTextTenDeTai);
        spnChude = findViewById(R.id.spinnerCD);
        ngayMoDangKy = findViewById(R.id.editTextNgayMoDangKy);
        editTextNgayBatDau = findViewById(R.id.editTextNgayBatDau);
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
                String tenDeTai = SuaDeTaiActivity.this.tenDeTai.getText().toString();
                String ngayMoDangKy = SuaDeTaiActivity.this.ngayMoDangKy.getText().toString();
                String ngayKetThucDangKy = SuaDeTaiActivity.this.ngayKetThucDangKy.getText().toString();

                String ngayNghiemThu = SuaDeTaiActivity.this.ngayNghiemThu.getText().toString();
                String ngayBatDau = SuaDeTaiActivity.this.editTextNgayBatDau.getText().toString();
                String ngayHetHan = SuaDeTaiActivity.this.ngayHetHan.getText().toString();
                double kinhPhiDuKien = 0;
                int soLuongThanhVienToiDa = 0;
                try {
                    kinhPhiDuKien = Double.parseDouble(SuaDeTaiActivity.this.kinhPhiDuKien.getText().toString());
                    soLuongThanhVienToiDa = Integer.parseInt(SuaDeTaiActivity.this.soLuongThanhVienToiDa.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(SuaDeTaiActivity.this, "Nhập số hop le vào kinh phí hoặc số lượng thành viên", Toast.LENGTH_SHORT).show();
                    return;
                }

                Topic tp = ((Topic) spnChude.getSelectedItem());
                String datenow= "2024-01-01";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     datenow= LocalDate.now().toString();
                }

                if (ngayMoDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThucDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayBatDau.matches("\\d{4}-\\d{2}-\\d{2}") && ngayHetHan.matches("\\d{4}-\\d{2}-\\d{2}") && ngayNghiemThu.matches("\\d{4}-\\d{2}-\\d{2}")) {

                } else {
                    Toast.makeText(SuaDeTaiActivity.this, "Nhập ngày theo định dạng yyyy-mm-dd", Toast.LENGTH_SHORT).show();
                    return;
                }
                Project project = new Project(maDeTai,tenDeTai,datenow,null,soLuongThanhVienToiDa,ngayMoDangKy,ngayKetThucDangKy,ngayBatDau,ngayHetHan,ngayNghiemThu,kinhPhiDuKien,null,tp,null,false);
                APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
                apiService.updateProject(project).enqueue(new retrofit2.Callback<APIResponse<Project>>() {
                    @Override
                    public void onResponse(retrofit2.Call<APIResponse<Project>> call, retrofit2.Response<APIResponse<Project>> response) {
                        if (response.body().isSuccess()) {
                            Toast.makeText(SuaDeTaiActivity.this, "Sửa đề tài thành công", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(SuaDeTaiActivity.this, "Sửa đề tài thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<APIResponse<Project>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });


    }


    void loadintoSpinChude() {
        list = new ArrayList<>();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTopic().enqueue(new retrofit2.Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<List<Topic>>> call, retrofit2.Response<APIResponse<List<Topic>>> response) {
                if (response.body().isSuccess()) {
                    list = response.body().getResult();



                    ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(getApplicationContext(), R.layout.simple_topic,list){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setText(list.get(position).getName());
                            return view;
                        }

                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setText(list.get(position).getName());
                            return view;
                        }
                    };

                    spnChude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            Topic selectedA = (Topic) parent.getItemAtPosition(position);
                            String selectedId = selectedA.getTopicCode();
                            Toast.makeText(SuaDeTaiActivity.this, "Selected ID: " + selectedId, Toast.LENGTH_SHORT).show();
                            // Xử lý id được chọn ở đây
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Xử lý khi không có mục nào được chọn
                        }
                    });

                    spnChude.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<APIResponse<List<Topic>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
//    sua de tai

}