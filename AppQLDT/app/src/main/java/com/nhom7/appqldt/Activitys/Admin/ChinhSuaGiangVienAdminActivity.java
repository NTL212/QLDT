package com.nhom7.appqldt.Activitys.Admin;

import static android.R.layout.simple_spinner_dropdown_item;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaGiangVienAdminActivity extends AppCompatActivity {


    EditText etmagv, ethoten, etngaysinh, etcccd, etsdt, etemail, etdiachi, etpassword;
    Spinner spinnergioitinh, spinnerKhoa;
    String falculityCode, sex, username;

    Faculty faculty;

    String[] genders = {"Nam", "Nữ"};

    Button btnXacNhanChinhSua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_giang_vien_admin);

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
        username = sharedPreferences.getString("username","");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);

        AnhXa();
        getKhoa();
        getGioiTinh();
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("lecturerCode")) {
            String value = intent.getStringExtra("lecturerCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<LecturerAccount>> call = apiService.getLecturerById(value);
            call.enqueue(new Callback<APIResponse<LecturerAccount>>() {
                @Override
                public void onResponse(Call<APIResponse<LecturerAccount>> call, Response<APIResponse<LecturerAccount>> response) {
                    if (response.isSuccessful()) {
                        LecturerAccount lecturerAccount = response.body().getResult();
                        etmagv.setText(lecturerAccount.getLecturer().getLecturerCode());
                        ethoten.setText(lecturerAccount.getLecturer().getName());
                        etngaysinh.setText(lecturerAccount.getLecturer().getBirthday());
                        etcccd.setText(lecturerAccount.getLecturer().getIdNum());
                        etsdt.setText(lecturerAccount.getLecturer().getPhoneNum());
                        etemail.setText(lecturerAccount.getLecturer().getEmail());
                        etdiachi.setText(lecturerAccount.getLecturer().getAddress());
                        etpassword.setText(lecturerAccount.getAccount().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<LecturerAccount>> call, Throwable t) {

                }
            });
        }


        btnXacNhanChinhSua.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChinhSuaGiangVienAdminActivity.this)
                        .setTitle("Xác nhận chỉnh sửa")
                        .setMessage("Bạn có chắc chắn muốn chỉnh sửa giảng viên này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String lecturerCode = etmagv.getText().toString().trim();
                                String username = etmagv.getText().toString().trim();
                                String password = etpassword.getText().toString().trim();
                                String name = ethoten.getText().toString().trim();
                                String birthday = etngaysinh.getText().toString().trim();
                                String address = etdiachi.getText().toString().trim();
                                String idNum = etcccd.getText().toString().trim();
                                String phoneNum = etsdt.getText().toString().trim();
                                String email = etemail.getText().toString().trim();

                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.editAccount(
                                        username, password, name, birthday, address, idNum, phoneNum, email, sex, falculityCode
                                );
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(ChinhSuaGiangVienAdminActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChinhSuaGiangVienAdminActivity.this, DanhSachGiangVienAdminActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ChinhSuaGiangVienAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChinhSuaGiangVienAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();;
            }
        });

    }
    private void AnhXa(){
        etmagv = findViewById(R.id.et_chinhsuagiangvien_magiangvien_Admin);
        spinnerKhoa = findViewById(R.id.spinner_chinhsuagiangvien_khoa_Admin);
        ethoten = findViewById(R.id.et_chinhsuagiangvien_ten_Admin);
        etngaysinh = findViewById(R.id.et_chinhsuagiangvien_ngaysinh_Admin);
        etcccd = findViewById(R.id.et_chinhsuagiangvien_CCCD_Admin);
        etsdt = findViewById(R.id.et_chinhsuagiangvien_SDT_Admin);
        spinnergioitinh = findViewById(R.id.spinner_chinhsuagiangvien_GioiTinh_Admin);
        etemail = findViewById(R.id.et_chinhsuagiangvien_Email_Admin);
        etdiachi = findViewById(R.id.et_chinhsuagiangvien_diachi_Admin);
        etpassword = findViewById(R.id.et_chinhsuagiangvien_Password_Admin);
        btnXacNhanChinhSua = findViewById(R.id.btn_XacNhan_ChinhSuaGV_Admin);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
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

    private void getKhoa(){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Faculty>>> call = apiService.getAllFalculities();

        call.enqueue(new Callback<APIResponse<List<Faculty>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Faculty>>> call, Response<APIResponse<List<Faculty>>> response) {
                if (response.isSuccessful()){
                    List<Faculty> faculties = response.body().getResult();
                    List<String> facultiesName = new ArrayList<>();
                    for (Faculty faculty : faculties){
                        facultiesName.add(faculty.getName());
                    }

                    ArrayAdapter<String> adapter =  new ArrayAdapter<>(ChinhSuaGiangVienAdminActivity.this, android.R.layout.simple_spinner_item, facultiesName);

                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    spinnerKhoa.setAdapter(adapter);

                    spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            for (Faculty faculty : faculties ){
                                if(selectedItem == faculty.getName()) {
                                    ChinhSuaGiangVienAdminActivity.this.faculty = faculty;
                                    falculityCode = faculty.getFalculityCode();
                                }
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            faculty = faculties.get(0);
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<APIResponse<List<Faculty>>> call, Throwable t) {

            }
        });
    }
    private void getGioiTinh(){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnergioitinh.setAdapter(adapter);

        spinnergioitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = parent.getItemAtPosition(position).toString();
                sex = selectedGender;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String selectedGender = parent.getItemAtPosition(0).toString();
            }
        });
    }
}