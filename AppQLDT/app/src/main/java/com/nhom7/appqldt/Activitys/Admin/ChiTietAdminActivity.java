package com.nhom7.appqldt.Activitys.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.AdminDTO;
import com.nhom7.appqldt.Activitys.DangNhapActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Admin;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietAdminActivity extends AppCompatActivity {

    TextView tvmaadmin, tvmatkhau, tvhoten, tvngaysinh, tvcccd, tvdienthoai, tvemail, tvgioitinh, tvdiachi;

    Button btnChinhSuaAdmin, btnDangXuat;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_admin);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//Lấy giá trị được lưu giữ ra
        username = sharedPreferences.getString("username", "");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);

        AnhXa();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("adCode")) {
            String value = intent.getStringExtra("adCode");
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<AdminDTO>> call = apiService.getAdminByAdCode(value);

            call.enqueue(new Callback<APIResponse<AdminDTO>>() {
                @Override
                public void onResponse(Call<APIResponse<AdminDTO>> call, Response<APIResponse<AdminDTO>> response) {
                    AdminDTO adminDTO = response.body().getResult();
                    tvmaadmin.setText(adminDTO.getAdmin().getAdCode());
                    tvhoten.setText(adminDTO.getAdmin().getName());
                    tvngaysinh.setText(adminDTO.getAdmin().getBirthday());
                    tvcccd.setText(adminDTO.getAdmin().getIdNum());
                    tvdienthoai.setText(adminDTO.getAdmin().getPhoneNum());
                    tvemail.setText(adminDTO.getAdmin().getEmail());
                    tvgioitinh.setText(adminDTO.getAdmin().getSex());
                    tvdiachi.setText(adminDTO.getAdmin().getAddress());
                    tvmatkhau.setText(adminDTO.getAccount().getPassword());
                }

                @Override
                public void onFailure(Call<APIResponse<AdminDTO>> call, Throwable t) {

                }
            });
        }else {
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<AdminDTO>> call = apiService.getAdminByAdCode(username);

            call.enqueue(new Callback<APIResponse<AdminDTO>>() {
                @Override
                public void onResponse(Call<APIResponse<AdminDTO>> call, Response<APIResponse<AdminDTO>> response) {
                    AdminDTO adminDTO = response.body().getResult();
                    tvmaadmin.setText(adminDTO.getAdmin().getAdCode());
                    tvhoten.setText(adminDTO.getAdmin().getName());
                    tvngaysinh.setText(adminDTO.getAdmin().getBirthday());
                    tvcccd.setText(adminDTO.getAdmin().getIdNum());
                    tvdienthoai.setText(adminDTO.getAdmin().getPhoneNum());
                    tvemail.setText(adminDTO.getAdmin().getEmail());
                    tvgioitinh.setText(adminDTO.getAdmin().getSex());
                    tvdiachi.setText(adminDTO.getAdmin().getAddress());
                    tvmatkhau.setText(adminDTO.getAccount().getPassword());
                }

                @Override
                public void onFailure(Call<APIResponse<AdminDTO>> call, Throwable t) {

                }
            });
        }

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTietAdminActivity.this, DangNhapActivity.class);
                startActivity(intent);
            }
        });

        btnChinhSuaAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ChiTietAdminActivity.this, ChinhSuaAdminActivity.class);
                String adCode = tvmaadmin.getText().toString();
                Log.d("adCode", adCode);
                editIntent.putExtra("adCode", adCode);
                startActivity(editIntent);
            }
        });



    }
    private void AnhXa() {
        tvmaadmin = (TextView) findViewById(R.id.tv_chitietadmin_maadmin_Admin);
        tvhoten = (TextView) findViewById(R.id.tv_chitietadmin_ten_Admin);
        tvngaysinh = (TextView) findViewById(R.id.tv_chitietadmin_ngaysinh_Admin);
        tvcccd = (TextView) findViewById(R.id.tv_chitietadmin_CCCD_Admin);
        tvdienthoai = (TextView) findViewById(R.id.tv_chitietadmin_SDT_Admin);
        tvemail = (TextView) findViewById(R.id.tv_chitietadmin_Email_Admin);
        tvgioitinh = (TextView) findViewById(R.id.tv_chitietadmin_GioiTinh_Admin);
        tvdiachi = (TextView) findViewById(R.id.tv_chitietadmin_diachi_Admin);
        tvmatkhau = (TextView) findViewById(R.id.tv_chitietadmin_matkhau_Admin);
        btnChinhSuaAdmin = (Button) findViewById(R.id.btn_ChinhSuaAdmin_Admin);
        btnDangXuat = (Button) findViewById(R.id.btn_DangXuat_Admin);
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
}