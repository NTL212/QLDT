package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DangNhapActivity;
import com.nhom7.appqldt.Activitys.GiangVien.GVGuiThongBaoActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ThongBaoDaGuiActivity;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Notification;
import com.nhom7.appqldt.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GuiThongBaoActivity extends AppCompatActivity {
    EditText nguoigui, nguoinhan, tieude, noidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_thong_bao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });
        nguoinhan = findViewById(R.id.edtNguoiNhan);
        tieude = findViewById(R.id.edtTieuDe);
        noidung = findViewById(R.id.edtNoiDung);
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        String nguoiGui = sharedPreferences.getString("username", "");
        findViewById(R.id.btnGui).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Gửi thông báo
                String nguoiNhan = nguoinhan.getText().toString();
                String tieuDe = tieude.getText().toString();
                String noiDung = noidung.getText().toString();
                APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
                apiService.sendMessageManager(nguoiGui, nguoiNhan, tieuDe, noiDung).enqueue(new Callback<APIResponse<Notification>>() {
                    @Override
                    public void onResponse(Call<APIResponse<Notification>> call, Response<APIResponse<Notification>> response) {
                        if (response.body().isSuccess()) {
                            //Gửi thông báo
//                            show dialog
                            DialogHelper.showDialog(GuiThongBaoActivity.this, "Thông báo", "Thông báo đã được gửi", "OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý sự kiện khi người dùng nhấn nút Positive
                                    dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                }
                            });

                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<Notification>> call, Throwable t) {

                    }
                });
                //Gửi thông báo
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_approveProject) {
            intent = new Intent(this, PheDuyetDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_manageProject) {
            intent = new Intent(this, QuanLyDeTaiActivity.class);
            startActivity(intent);
            return true;
        }else if (id ==R.id.action_sendedNotification){
            intent = new Intent(this, ThongBaoDaGui_QLActivity.class);
            startActivity(intent);
            return true;
        }else if (id ==R.id.action_receivedNotification){
            intent = new Intent(this, ThongBaoNhan_QLActivity.class);
            startActivity(intent);
            return true;
        }else
        if (id == R.id.action_projectTopic) {
            intent = new Intent(this, ListChuDeActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_logout) {
            intent = new Intent(this, DangNhapActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}