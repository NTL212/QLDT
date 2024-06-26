package com.nhom7.appqldt.Activitys.GiangVien;

import static android.R.layout.simple_spinner_dropdown_item;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GVGuiThongBaoActivity extends AppCompatActivity {

    String username;
    EditText nguoigui, nguoinhan, tieude, noidung;
    TextView tvMaNguoiNhan;
    Button btnGuiTB;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvgui_thong_bao);

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
        getAllAccount();
        btnGuiTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                String receiveId = spinner.getSelectedItem().toString().trim();
                String title = tieude.getText().toString().trim();
                String content = noidung.getText().toString().trim();

                Call<APIResponse<Notification>> call = apiService.sendMessageLecture(username, receiveId, title, content);
                call.enqueue(new Callback<APIResponse<Notification>>() {
                    @Override
                    public void onResponse(Call<APIResponse<Notification>> call, Response<APIResponse<Notification>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().isSuccess()) {
                                Notification notification = response.body().getResult();
                                DialogHelper.showDialog(GVGuiThongBaoActivity.this, // Context của Activity hiện tại
                                        "Thông báo", // Tiêu đề của dialog
                                        "Gửi thông báo cho " + notification.getReceiver().getUsername() + " thành công", // Nội dung của dialog
                                        "OK", // Text của nút Positive
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Xử lý sự kiện khi người dùng nhấn nút Positive
                                                dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                                Intent intent = new Intent(GVGuiThongBaoActivity.this, ThongBaoDaGuiActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                            } else {
                                DialogHelper.showDialog(GVGuiThongBaoActivity.this, // Context của Activity hiện tại
                                        "Thông báo", // Tiêu đề của dialog
                                        "Gửi thông báo thất bại", // Nội dung của dialog
                                        "OK", // Text của nút Positive
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                // Xử lý sự kiện khi người dùng nhấn nút Positive
                                                dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                            }
                                        });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<Notification>> call, Throwable t) {

                    }
                });
            }
        });


    }

    private void AnhXa() {
        nguoinhan = findViewById(R.id.edtNguoiNhan);
        tieude = findViewById(R.id.edtTieuDe);
        noidung = findViewById(R.id.edtNoiDung);
        btnGuiTB = findViewById(R.id.btnGui);
        spinner = findViewById(R.id.spinnerNguoiNhan);
        tvMaNguoiNhan = findViewById(R.id.tv_TB_MaNguoiNhan);
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

    private void getAllAccount(){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Account>>> call = apiService.getAllAcc();
        call.enqueue(new Callback<APIResponse<List<Account>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Account>>> call, Response<APIResponse<List<Account>>> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        List<Account> listAcc = response.body().getResult();
                        List<String> accNames = new ArrayList<>();
                        for (Account acc : listAcc) {
                            if(!acc.getRole().contains("ROLE_ADMIN")){
                                accNames.add(acc.getUsername());
                            }

                        }
                        // Tạo adapter cho Spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(GVGuiThongBaoActivity.this, android.R.layout.simple_spinner_item, accNames);

                        // Thiết lập layout cho dropdown menu
                        adapter.setDropDownViewResource(simple_spinner_dropdown_item);

                        // Gắn adapter vào Spinner

                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parentView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Account>>> call, Throwable t) {

            }
        });
    }
}