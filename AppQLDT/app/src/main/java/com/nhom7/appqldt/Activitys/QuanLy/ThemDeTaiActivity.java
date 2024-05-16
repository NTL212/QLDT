package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThemDeTaiActivity extends AppCompatActivity {
    EditText tvMaDeTai, tvTenDeTai,tvngaymodangky,tvngayketthucdangky,tvngaybatdau,tvngayketthuc,tvngaynghiemthu,
    tvngansach,tvsoluongtvmax;
    Spinner spnChude;
    List<Topic> list ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_de_tai);
        tvMaDeTai = findViewById(R.id.editTextMaDeTai);
        tvTenDeTai = findViewById(R.id.editTextTenDeTai);
        tvngaymodangky = findViewById(R.id.editTextNgayMoDangKy);
        tvngayketthucdangky = findViewById(R.id.editTextNgayKetThucDangKy);
        tvngaybatdau = findViewById(R.id.editTextNgayBatDau);
        tvngayketthuc = findViewById(R.id.editTextNgayHetHan);
        tvngaynghiemthu = findViewById(R.id.editTextNgayNghiemThu);
        tvngansach = findViewById(R.id.editTextKinhPhiDuKien);
        tvsoluongtvmax = findViewById(R.id.editTextSoLuongTVToiDa);
        spnChude = findViewById(R.id.spinnerChuDe);

        loadintoSpinChude();

        findViewById(R.id.buttonThemDeTai).setOnClickListener(v -> {
            String datenow= "2024-01-01";
            String maDeTai = tvMaDeTai.getText().toString();
            String tenDeTai = tvTenDeTai.getText().toString();
            String ngayMoDangKy = tvngaymodangky.getText().toString().equals("") ? datenow:tvngaymodangky.getText().toString();
            String ngayKetThucDangKy = tvngayketthucdangky.getText().toString().equals("") ? datenow:tvngayketthucdangky.getText().toString();

            String ngayBatDau = tvngaybatdau.getText().toString().equals("") ? datenow:tvngaybatdau.getText().toString();
            String ngayKetThuc = tvngayketthuc.getText().toString().equals("") ? datenow:tvngayketthuc.getText().toString();
            String ngayNghiemThu = tvngaynghiemthu.getText().toString().equals("") ? datenow:tvngaynghiemthu.getText().toString();
            double nganSach=0;
            int soLuongTVMax=0;
            try {
                nganSach = Double.parseDouble(tvngansach.getText().toString());
                soLuongTVMax = Integer.parseInt(tvsoluongtvmax.getText().toString());
            }
            catch (Exception e){
                Toast.makeText(ThemDeTaiActivity.this, "Nhập số hop le vào kinh phí hoặc số lượng thành viên", Toast.LENGTH_SHORT).show();
                return;
            }

            Topic topic = (Topic) spnChude.getSelectedItem();
            Intent intent = new Intent();
            //neu nhap ngay hop le yyyy-mm-dd
            if (ngayMoDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThucDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayBatDau.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThuc.matches("\\d{4}-\\d{2}-\\d{2}") && ngayNghiemThu.matches("\\d{4}-\\d{2}-\\d{2}")) {
                Project project = new Project(maDeTai,tenDeTai,null,"",soLuongTVMax,ngayMoDangKy,ngayKetThucDangKy,ngayBatDau,ngayKetThuc,ngayNghiemThu,nganSach,null,topic,null,true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("project", project);
                setResult(RESULT_OK, new Intent().putExtras(bundle));
                finish();
            } else {
                Toast.makeText(ThemDeTaiActivity.this, "Nhập ngày theo định dạng yyyy-mm-dd", Toast.LENGTH_SHORT).show();
                return;
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
                            Toast.makeText(ThemDeTaiActivity.this, "Selected ID: " + selectedId, Toast.LENGTH_SHORT).show();
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
}