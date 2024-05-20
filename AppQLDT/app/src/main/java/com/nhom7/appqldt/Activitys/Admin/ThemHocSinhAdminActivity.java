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
import com.nhom7.appqldt.Helpers.CheckHepler;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Class;
import com.nhom7.appqldt.Models.Faculty;
import com.nhom7.appqldt.Models.Major;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemHocSinhAdminActivity extends AppCompatActivity {

    EditText etmahs, ethoten, etngaysinh, etcccd, etsdt, etemail, etdiachi;

    Spinner spinnerKhoa, spinnergioitinh, spinnernganh, spinnerlop;
    String username, sex, falculityCode, majorCode, classCode;


    Button btn_themHS;

    Faculty faculty;

    Major major;

    Class class1;

    String[] genders = {"Nam", "Nữ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoc_sinh_admin);

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

        btn_themHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ThemHocSinhAdminActivity.this)
                        .setTitle("Xác nhận Thêm")
                        .setMessage("Bạn có chắc chắn muốn thêm sinh viên này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String studentCode = etmahs.getText().toString();
                                String name = ethoten.getText().toString();
                                String birthday = etngaysinh.getText().toString(); // Đảm bảo định dạng ngày tháng đúng
                                String address = etdiachi.getText().toString();
                                String idNum = etcccd.getText().toString();
                                String phoneNum = etsdt.getText().toString();
                                if(CheckHepler.checkCCCD(idNum)){
                                    Toast.makeText(ThemHocSinhAdminActivity.this, "Nhập lại cccd/cmnd 12 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if(CheckHepler.checkPhone(phoneNum)){
                                    Toast.makeText(ThemHocSinhAdminActivity.this, "Nhập lại so dien thoai 10 số", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                String email = etemail.getText().toString();
                                String sex = spinnergioitinh.getSelectedItem().toString();
                                String classCode = ThemHocSinhAdminActivity.this.classCode; // Sử dụng classCode từ spinner lớp


                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                // Gọi phương thức insertStudent
                                Call<APIResponse<String>> call = apiService.insertStudent(studentCode, name, birthday, address, idNum, phoneNum, email, sex, classCode);
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(ThemHocSinhAdminActivity.this, "Thêm học sinh thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ThemHocSinhAdminActivity.this, DanhSachHocSinhAdminActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ThemHocSinhAdminActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ThemHocSinhAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        })
                        .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn "No"
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
        etmahs = findViewById(R.id.et_themhocsinh_mahocsinh_Admin);
        spinnerKhoa = findViewById(R.id.spinner_themhocsinh_khoa_Admin);
        ethoten = findViewById(R.id.et_themhocsinh_ten_Admin);
        etngaysinh = findViewById(R.id.et_themhocsinh_ngaysinh_Admin);
        etcccd = findViewById(R.id.et_themhocsinh_CCCD_Admin);
        etsdt = findViewById(R.id.et_themhocsinh_SDT_Admin);
        spinnergioitinh = findViewById(R.id.spinner_themhocsinh_GioiTinh_Admin);
        etemail = findViewById(R.id.et_themhocsinh_Email_Admin);
        etdiachi = findViewById(R.id.et_themhocsinh_diachi_Admin);
        spinnernganh = findViewById(R.id.spinner_themhocsinh_nganh_Admin);
        spinnerlop = findViewById(R.id.spinner_themhocsinh_lop_Admin);
        btn_themHS = findViewById(R.id.btn_ThemHS_Admin);
    }

    private void getKhoa(OnFacultySelectedListener listener) {
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ThemHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, facultiesName);

                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    spinnerKhoa.setAdapter(adapter);

                    spinnerKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            for (Faculty faculty : faculties) {
                                if (selectedItem == faculty.getName()) {
                                    ThemHocSinhAdminActivity.this.faculty = faculty;
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


    private void getNganh(OnMajorSelectedListener listener) {
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ThemHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, majorsName);

                    adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                    spinnernganh.setAdapter(adapter);

                    spinnernganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            for (Major major : majors) {
                                if (selectedItem == major.getName()) {
                                    ThemHocSinhAdminActivity.this.major = major;
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

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ThemHocSinhAdminActivity.this, android.R.layout.simple_spinner_item, classesName);

                        adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                        spinnerlop.setAdapter(adapter);

                        spinnerlop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedItem = (String) parent.getItemAtPosition(position);
                                for (Class class1 : classes) {
                                    if (selectedItem == class1.getName()) {
                                        ThemHocSinhAdminActivity.this.class1 = class1;
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