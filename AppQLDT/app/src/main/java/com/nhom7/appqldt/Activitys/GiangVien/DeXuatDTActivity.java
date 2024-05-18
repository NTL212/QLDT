package com.nhom7.appqldt.Activitys.GiangVien;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.DangNhapHelper;
import com.nhom7.appqldt.Helpers.DateHelper;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.Lecturer;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeXuatDTActivity extends AppCompatActivity {

    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietChuDe;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa;
    Button btnDeXuat;
    String username;
    Lecturer lecturer;
    Spinner spinner;

    Topic topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_de_xuat_dtactivity);

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
        username = sharedPreferences.getString("username", "");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);

        AnhXa();
        getChuDe();
        getLecture();
        btnDeXuat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try{
                    Project project = new Project();
                    project.setProjectCode(tvChiTietMaDeTai.getText().toString());
                    project.setName(tvChiTietTenDeTai.getText().toString());
                    project.setDescription(tvChiTietMoTa.getText().toString());
                    project.setMaxMember(Integer.valueOf(tvChiTieSoThanhVien.getText().toString()));
                    project.setStartDate(DateHelper.convertToStandardDate(tvChiTietNgayBatDau.getText().toString().trim()));
                    project.setEndDate(DateHelper.convertToStandardDate(tvChiTietNgayKetThuc.getText().toString().trim()));
                    project.setEstBudget(Double.valueOf(tvChiTietKinhPhi.getText().toString()));

                    project.setTopic(topic);
                    project.setLecturer(lecturer);

                    project.setProposed(false);

                    APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                    Call<APIResponse<Project>> call = apiService.proposeProjectForLecturer(project);

                    call.enqueue(new Callback<APIResponse<Project>>() {
                        @Override
                        public void onResponse(Call<APIResponse<Project>> call, Response<APIResponse<Project>> response) {
                            if (response.isSuccessful()) {
                                APIResponse<Project> projectAPIResponse = response.body();
                                if (projectAPIResponse.isSuccess()) {
                                    DialogHelper.showDialog(DeXuatDTActivity.this, // Context của Activity hiện tại
                                            "Thông báo", // Tiêu đề của dialog
                                            "Đề xuất đề tài " + project.getProjectCode() + " thành công", // Nội dung của dialog
                                            "OK", // Text của nút Positive
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Xử lý sự kiện khi người dùng nhấn nút Positive
                                                    dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                                    Intent intent = new Intent(DeXuatDTActivity.this, ListDeTaiCuaToiActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                } else {
                                    DialogHelper.showFailedDialog(DeXuatDTActivity.this, projectAPIResponse.getMessage());
                                }
                                ;
                            } else {
                                DialogHelper.showFailedDialog(DeXuatDTActivity.this, "Thất bại");
                            }
                        }

                        @Override
                        public void onFailure(Call<APIResponse<Project>> call, Throwable t) {
                            DialogHelper.showFailedDialog(DeXuatDTActivity.this, "Không gọi được API");
                        }
                    });
                }catch (Exception e){
                    DialogHelper.showFailedDialog(DeXuatDTActivity.this, "Vui lòng nhập đủ thông tin");
                }
            }
        });

    }


    private void AnhXa() {
        tvChiTietMaDeTai = (TextView) findViewById(R.id.tvChiTietMaDeTai);
        tvChiTietTenDeTai = (TextView) findViewById(R.id.tvChiTietTenDeTai);
        tvChiTietChuDe = (TextView) findViewById(R.id.tvChiTietChuDe);
        tvChiTietNgayBatDau = (TextView) findViewById(R.id.tvChiTietNgayBatDau);
        tvChiTietNgayKetThuc = (TextView) findViewById(R.id.tvChiTietNgayKetThuc);
        tvChiTietKinhPhi = (TextView) findViewById(R.id.tvChiTietKinhPhi);
        tvChiTieSoThanhVien = (TextView) findViewById(R.id.tvChiTieSoThanhVien);
        tvChiTietMoTa = (TextView) findViewById(R.id.tvChiTietMoTa);
        btnDeXuat = (Button) findViewById(R.id.btnDeXuat);
        spinner = findViewById(R.id.spinnerChuDe);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean sItem = MenuHelper.ChonItem(this, item);
        if (sItem) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getLecture() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<Lecturer>> call = apiService.getLecturerByID(username);
        call.enqueue(new Callback<APIResponse<Lecturer>>() {
            @Override
            public void onResponse(Call<APIResponse<Lecturer>> call, Response<APIResponse<Lecturer>> response) {
                if (response.isSuccessful()) {
                    lecturer = response.body().getResult();
                } else {
                    lecturer = null;
                }
            }

            @Override
            public void onFailure(Call<APIResponse<Lecturer>> call, Throwable t) {
                lecturer = null;
            }
        });
    }

    private void getChuDe() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Topic>>> call = apiService.getAllActiveTopic();
        call.enqueue(new Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Topic>>> call, Response<APIResponse<List<Topic>>> response) {
                if (response.isSuccessful()) {
                    List<Topic> list = response.body().getResult();
                    List<String> topicNames = new ArrayList<>();
                    for (Topic topic : list) {
                        topicNames.add(topic.getName());
                    }

                    // Tạo adapter cho Spinner
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(DeXuatDTActivity.this, android.R.layout.simple_spinner_item, topicNames);

                    // Thiết lập layout cho dropdown menu
                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    // Gắn adapter vào Spinner
                    spinner.setAdapter(adapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            // Xử lý khi người dùng chọn một mục trong Spinner
                            String selectedItem = (String) parentView.getItemAtPosition(position);
                            for (Topic topic : list) {
                                if(selectedItem == topic.getName()){
                                    DeXuatDTActivity.this.topic = topic;
                                }
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                           topic = list.get(1);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<APIResponse<List<Topic>>> call, Throwable t) {

            }
        });
    }
}