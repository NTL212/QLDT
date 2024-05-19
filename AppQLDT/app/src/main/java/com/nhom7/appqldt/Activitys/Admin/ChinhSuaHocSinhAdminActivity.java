package com.nhom7.appqldt.Activitys.Admin;

import static android.R.layout.simple_spinner_dropdown_item;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.nhom7.appqldt.Models.Class;
import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.Major;
import com.nhom7.appqldt.Models.Student;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChinhSuaHocSinhAdminActivity extends AppCompatActivity {

    EditText etmahs, ethoten, etngaysinh, etcccd, etsdt, etemail, etdiachi;

    Spinner spinnerKhoa, spinnergioitinh, spinnernganh, spinnerlop;
    String username, sex, falculityCode, majorCode, classCode;


    Button btn_chinhsuaHS;

    Faculty faculty;

    Major major;

    Class class1;

    String[] genders = {"Nam", "Nữ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_hoc_sinh_admin);

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

        getGioiTinh();
        getKhoa(this::onFacultySelected);
        getLop();

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("studentCode")) {
            String value = intent.getStringExtra("studentCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<Student>> call = apiService.getStudentByid(value);

            call.enqueue(new Callback<APIResponse<Student>>() {
                @Override
                public void onResponse(Call<APIResponse<Student>> call, Response<APIResponse<Student>> response) {
                    Student student = response.body().getResult();
                    etmahs.setText(student.getStudentCode());
                    ethoten.setText(student.getName());
                    etngaysinh.setText(student.getBirthday());
                    etdiachi.setText(student.getAddress());
                    etcccd.setText(student.getIdNum());
                    etsdt.setText(student.getPhoneNum());
                    etemail.setText(student.getEmail());
                }

                @Override
                public void onFailure(Call<APIResponse<Student>> call, Throwable t) {

                }
            });
        }

        btn_chinhsuaHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChinhSuaHocSinhAdminActivity.this)
                        .setTitle("Xác nhận chỉnh sửa")
                        .setMessage("Bạn có chắc chắn muốn chỉnh sửa học sinh này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String studentCode = etmahs.getText().toString().trim();
                                String name = ethoten.getText().toString().trim();
                                String birthday = etngaysinh.getText().toString().trim();
                                String address = etdiachi.getText().toString().trim();
                                String idNum = etcccd.getText().toString().trim();
                                String phoneNum = etsdt.getText().toString().trim();
                                String email = etemail.getText().toString().trim();
                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.updateStudent(
                                        studentCode, name, birthday, address, idNum, phoneNum,
                                        email, sex, classCode
                                );

                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        Toast.makeText(ChinhSuaHocSinhAdminActivity.this, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ChinhSuaHocSinhAdminActivity.this, DanhSachHocSinhAdminActivity.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChinhSuaHocSinhAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

    }
    public void onFacultySelected(String falculityCode) {
        this.falculityCode = falculityCode;
        Log.d("FalculityCode", "Selected Falculity Code: " + falculityCode);
        getNganh(this::onMajorSelected); // Gọi getNganh() sau khi falculityCode được cập nhật
        resetSpinner(spinnernganh);
        resetSpinner(spinnerlop);
    }

    public void onMajorSelected(String majorCode) {
        this.majorCode = majorCode;
        Log.d("MajorCode", "Selected Major Code: " + majorCode);
        getLop();
        resetSpinner(spinnerlop);
    }

    private void resetSpinner(Spinner spinner) {
        ArrayAdapter<String> emptyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        emptyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(emptyAdapter);
    }

    private void AnhXa() {
        etmahs = findViewById(R.id.et_chinhsuahocsinh_mahocsinh_Admin);
        spinnerKhoa = findViewById(R.id.spinner_chinhsuahocsinh_khoa_Admin);
        ethoten = findViewById(R.id.et_chinhsuahocsinh_ten_Admin);
        etngaysinh = findViewById(R.id.et_chinhsuahocsinh_ngaysinh_Admin);
        etcccd = findViewById(R.id.et_chinhsuahocsinh_CCCD_Admin);
        etsdt = findViewById(R.id.et_chinhsuahocsinh_SDT_Admin);
        spinnergioitinh = findViewById(R.id.spinner_chinhsuahocsinh_GioiTinh_Admin);
        etemail = findViewById(R.id.et_chinhsuahocsinh_Email_Admin);
        etdiachi = findViewById(R.id.et_chinhsuahocsinh_diachi_Admin);
        spinnernganh = findViewById(R.id.spinner_chinhsuahocsinh_nganh_Admin);
        spinnerlop = findViewById(R.id.spinner_chinhsuahocsinh_lop_Admin);
        btn_chinhsuaHS = findViewById(R.id.btn_ChinhSuaHS_Admin);
    }

    private void getKhoa(ThemHocSinhAdminActivity.OnFacultySelectedListener listener) {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Faculty>>> call = apiService.getAllFalculities();

        call.enqueue(new Callback<APIResponse<List<Faculty>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Faculty>>> call, Response<APIResponse<List<Faculty>>> response) {
                if (response.isSuccessful()) {
                    List<Faculty> faculties = response.body().getResult();
                    List<String> facultiesName = new ArrayList<>();
                    for (Faculty faculty : faculties) {
                        facultiesName.add(faculty.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ChinhSuaHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, facultiesName);

                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    spinnerKhoa.setAdapter(adapter);

                    spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            for (Faculty faculty : faculties) {
                                if (selectedItem == faculty.getName()) {
                                    ChinhSuaHocSinhAdminActivity.this.faculty = faculty;
                                    falculityCode = faculty.getFalculityCode();
                                    listener.onFacultySelected(falculityCode);
                                    Log.d("faculityCode123", falculityCode);
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


    private void getNganh(ThemHocSinhAdminActivity.OnMajorSelectedListener listener) {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Major>>> call = apiService.getMajorByFalculityCode(falculityCode);
        Log.d("falculityCode", falculityCode);

        call.enqueue(new Callback<APIResponse<List<Major>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Major>>> call, Response<APIResponse<List<Major>>> response) {
                if (response.isSuccessful()) {
                    List<Major> majors = response.body().getResult();
                    List<String> majorsName = new ArrayList<>();
                    for (Major major : majors) {
                        majorsName.add(major.getName());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ChinhSuaHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, majorsName);

                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    spinnernganh.setAdapter(adapter);

                    spinnernganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            for (Major major : majors) {
                                if (selectedItem == major.getName()) {
                                    ChinhSuaHocSinhAdminActivity.this.major = major;
                                    majorCode = major.getMajorCode();
                                    listener.onMajorSelected(majorCode);
                                }
                            }
                        }


                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            major = majors.get(0);
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Major>>> call, Throwable t) {

            }
        });
    }

    private void getLop() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Class>>> call = apiService.getClassByMajorCode(majorCode);
        call.enqueue(new Callback<APIResponse<List<Class>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Class>>> call, Response<APIResponse<List<Class>>> response) {
                if (response.isSuccessful()) {
                    List<Class> classes = response.body().getResult();
                    List<String> classesName = new ArrayList<>();
                    for (Class class2 : classes) {
                        classesName.add(class2.getName());

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ChinhSuaHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, classesName);

                        adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                        spinnerlop.setAdapter(adapter);

                        spinnerlop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = (String) parent.getItemAtPosition(position);
                                for (Class class1 : classes) {
                                    if (selectedItem == class1.getName()) {
                                        ChinhSuaHocSinhAdminActivity.this.class1 = class1;
                                        classCode = class1.getClassCode();
                                        break;
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                if (!classes.isEmpty()) {
                                    class1 = classes.get(0);
                                    classCode = class1.getClassCode();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Class>>> call, Throwable t) {

            }
        });

    }

    private void getGioiTinh() {

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

    public interface OnFacultySelectedListener {
        void onFacultySelected(String falculityCode);
    }

    public interface OnMajorSelectedListener {
        void onMajorSelected(String majorCode);
    }

}