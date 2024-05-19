package com.nhom7.appqldt.Activitys.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.ManagerAccount;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietQuanLyAdminActivity extends AppCompatActivity {
    TextView tvmaquanly, tvhoten, tvngaysinh, tvcccd, tvdienthoai, tvemail, tvgioitinh, tvdiachi, tvusername, tvpassword;

    String username;

    Button btnXoaQL, btnChinhSuaQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_quan_ly_admin);
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

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("empCode")) {
            String value = intent.getStringExtra("empCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<ManagerAccount>> call = apiService.getManagerById(value);
            call.enqueue(new Callback<APIResponse<ManagerAccount>>() {
                @Override
                public void onResponse(Call<APIResponse<ManagerAccount>> call, Response<APIResponse<ManagerAccount>> response) {
                    if (response.isSuccessful()){
                        ManagerAccount managerAccount = response.body().getResult();
                        tvmaquanly.setText(managerAccount.getManagementStaff().getEmpCode());
                        tvhoten.setText(managerAccount.getManagementStaff().getName());
                        tvngaysinh.setText(managerAccount.getManagementStaff().getBirthday());
                        tvcccd.setText(managerAccount.getManagementStaff().getIdNum());
                        tvdienthoai.setText(managerAccount.getManagementStaff().getPhoneNum());
                        tvemail.setText(managerAccount.getManagementStaff().getEmail());
                        tvgioitinh.setText(managerAccount.getManagementStaff().getSex());
                        tvdiachi.setText(managerAccount.getManagementStaff().getAddress());
                        tvusername.setText(managerAccount.getAccount().getUsername());
                        tvpassword.setText(managerAccount.getAccount().getPassword());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<ManagerAccount>> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        btnXoaQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ChiTietQuanLyAdminActivity.this)
                        .setTitle("Xác nhận xoá")
                        .setMessage("Bạn có chắc chắn muốn xóa quàn lý này không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                Call<APIResponse<String>> call = apiService.deleteAccount((String) tvmaquanly.getText());
                                call.enqueue(new Callback<APIResponse<String>>() {
                                    @Override
                                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                        if (response.isSuccessful()) {
                                            Toast.makeText(ChiTietQuanLyAdminActivity.this, "Xoá quản lý thành công", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ChiTietQuanLyAdminActivity.this, DanhSachQuanLyAdminActivity.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(ChiTietQuanLyAdminActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                        Toast.makeText(ChiTietQuanLyAdminActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        })
                        .setNegativeButton(android.R.string.no, null) // Không làm gì khi nhấn "No"
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });
        btnChinhSuaQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ChiTietQuanLyAdminActivity.this, ChinhSuaQuanLyAdminActivity.class);
                String empCode = tvmaquanly.getText().toString();
                Log.d("EmpCode", empCode);
                editIntent.putExtra("empCode", empCode);
                startActivity(editIntent);
            }
        });


    }
    private void AnhXa(){
        tvmaquanly = (TextView) findViewById(R.id.tv_chitietquanly_maquanly_Admin);
        tvhoten = (TextView) findViewById(R.id.tv_chitietquanly_ten_Admin);
        tvngaysinh = (TextView) findViewById(R.id.tv_chitietquanly_ngaysinh_Admin);
        tvcccd = (TextView) findViewById(R.id.tv_chitietquanly_CCCD_Admin);
        tvdienthoai = (TextView) findViewById(R.id.tv_chitietquanly_SDT_Admin);
        tvemail = (TextView) findViewById(R.id.tv_chitietquanly_Email_Admin);
        tvgioitinh = (TextView) findViewById(R.id.tv_chitietquanly_GioiTinh_Admin);
        tvdiachi = (TextView) findViewById(R.id.tv_chitietquanly_diachi_Admin);
        tvusername = (TextView) findViewById(R.id.tv_chitietquanly_Username_Admin);
        tvpassword = (TextView) findViewById(R.id.tv_chitietquanly_Password_Admin);
        btnXoaQL = (Button) findViewById(R.id.btn_XoaQL_Admin);
        btnChinhSuaQL = (Button) findViewById(R.id.btn_ChinhSuaQL_Admin);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean sItem = MenuHelper.ChonItem(this, item);
        if(sItem){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}