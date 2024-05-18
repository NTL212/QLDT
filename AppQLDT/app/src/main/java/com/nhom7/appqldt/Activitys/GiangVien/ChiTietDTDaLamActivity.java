package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.FileDTO;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;
import com.nhom7.appqldt.RealPathUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDTDaLamActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 1;
    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietNguoiDang, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa, tvChiTietFileDinhKem, tvChiTietFileBaoCao,tvChiTietKetQua,tvChiTietHoiDong, tvChiTietNhanXet  ;
    Button btnChiTietFileBaoCao, btnChiTietFileDinhKem,btnNopBaoCao;

    String projectCode;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dtda_lam);

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
        if (intent != null && intent.hasExtra("projectCode")) {
            projectCode = intent.getStringExtra("projectCode"); // Lấy dữ liệu từ Intent
            // Xử lý dữ liệu ở đây
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<Project>> call = apiService.getProjectDetailForLecturer(projectCode);
            call.enqueue(new Callback<APIResponse<Project>>() {
                @Override
                public void onResponse(Call<APIResponse<Project>> call, Response<APIResponse<Project>> response) {
                    if(response.isSuccessful()){
                        Project project = response.body().getResult();
                        tvChiTietMaDeTai.setText(project.getProjectCode());
                        tvChiTietTenDeTai.setText(project.getName());
                        tvChiTietChuDe.setText(project.getTopic().getName());
                        tvChiTietNgayDang.setText(project.getCreateDate());
                        tvChiTietNgayKetThucDang.setText(project.getCloseRegDate());
                        tvChiTietNgayBatDau.setText(project.getStartDate());
                        tvChiTietNgayKetThuc.setText(project.getEndDate());
                        tvChiTietNgayNghiemThu.setText(project.getAcceptanceDate());
                        tvChiTietKinhPhi.setText(String.valueOf(project.getEstBudget()));
                        tvChiTieSoThanhVien.setText(String.valueOf(project.getMaxMember()));
                        tvChiTietNguoiDang.setText(project.getLecturer().getName());
                        tvChiTietNgayMoDang.setText(project.getOpenRegDate());
                        tvChiTietMoTa.setText(project.getDescription());
                        tvChiTietKetQua.setText(project.getResult());
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<Project>> call, Throwable t) {

                }
            });
        }


        btnChiTietFileBaoCao.setVisibility(View.GONE);
        tvChiTietFileBaoCao.setVisibility(View.VISIBLE);

        btnNopBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Loại file
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Chọn file"), PICK_FILE_REQUEST_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Xử lý khi không có ứng dụng nào có thể xử lý Intent này
                }
            }
        });
        btnChiTietFileDinhKem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Loại file
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Chọn file"), PICK_FILE_REQUEST_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Xử lý khi không có ứng dụng nào có thể xử lý Intent này
                }
            }
        });

        btnChiTietFileBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*"); // Loại file
                intent.addCategory(Intent.CATEGORY_OPENABLE);

                try {
                    startActivityForResult(Intent.createChooser(intent, "Chọn file"), PICK_FILE_REQUEST_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    // Xử lý khi không có ứng dụng nào có thể xử lý Intent này
                }
            }
        });

    }

    private void AnhXa(){
        tvChiTietMaDeTai = (TextView) findViewById(R.id.tvChiTietMaDeTai);
        tvChiTietTenDeTai = (TextView) findViewById(R.id.tvChiTietTenDeTai);
        tvChiTietChuDe = (TextView) findViewById(R.id.tvChiTietChuDe);
        tvChiTietNgayDang = (TextView) findViewById(R.id.tvChiTietNgayDang);
        tvChiTietNgayKetThucDang = (TextView) findViewById(R.id.tvChiTietNgayKetThucDang);
        tvChiTietNgayBatDau = (TextView) findViewById(R.id.tvChiTietNgayBatDau);
        tvChiTietNgayKetThuc = (TextView) findViewById(R.id.tvChiTietNgayKetThuc);
        tvChiTietNgayNghiemThu = (TextView) findViewById(R.id.tvChiTietNgayNghiemThu);
        tvChiTietKinhPhi = (TextView) findViewById(R.id.tvChiTietKinhPhi);
        tvChiTieSoThanhVien = (TextView) findViewById(R.id.tvChiTieSoThanhVien);
        tvChiTietNguoiDang = (TextView) findViewById(R.id.tvChiTietNguoiDang);
        tvChiTietNgayMoDang = (TextView) findViewById(R.id.tvChiTietNgayMoDang);
        tvChiTietMoTa = (TextView) findViewById(R.id.tvChiTietMoTa);
        tvChiTietFileDinhKem = (TextView) findViewById(R.id.tvChiTietFileDinhKem);
        tvChiTietFileBaoCao = (TextView) findViewById(R.id.tvChiTietFileBaoCao);
        tvChiTietKetQua = (TextView) findViewById(R.id.tvChiTietKetQua);
        tvChiTietHoiDong = (TextView) findViewById(R.id.tvChiTietHoiDong);
        tvChiTietNhanXet = (TextView) findViewById(R.id.tvChiTietNhanXet);
        btnChiTietFileBaoCao = (Button) findViewById(R.id.btnChiTietFileBaoCao);
        btnChiTietFileDinhKem = (Button) findViewById(R.id.btnChiTietFileDinhKem);
        btnNopBaoCao = (Button) findViewById(R.id.btnNopBaoCao);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (data != null && data.getData() != null) {
                Uri fileUri = data.getData();
                if (fileUri != null) {
                    // Gọi phương thức để upload tệp tin đã chọn
                    uploadFile(fileUri);
                } else {
                    Toast.makeText(this, "Không thể truy cập tệp tin đã chọn", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Không có tệp tin nào được chọn", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Phương thức để lấy đường dẫn thực của file từ Uri
    private String getRealPathFromURI(Uri uri) {
        if (uri == null) {
            return null;
        }

        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            try {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(column_index);
                return path;
            } catch (IllegalArgumentException e) {
                // Xử lý khi không có cột Media.DATA
            } finally {
                cursor.close();
            }
        }

        // Nếu cursor null hoặc không có cột Media.DATA, trả về đường dẫn của uri
        return uri.getPath();
    }

    public void uploadFile(Uri fileUri) {
        // Mở luồng dữ liệu từ Uri
        InputStream inputStream = null;
        try {
            inputStream = getContentResolver().openInputStream(fileUri);
        } catch (FileNotFoundException e) {
            Log.e("file", "Không thể mở luồng dữ liệu từ Uri: " + e.getMessage());
            return;
        }

        // Tạo request body cho file
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-date"), inputStream.toString());
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("fileInput", "filename", requestFile);
        if(requestFile ==null || filePart ==null){
            Log.e("file", "Không thể mở luồng dữ liệu từ Uri: ");
            return;
        }
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        // Gọi API upload file

        Call<APIResponse<FileDTO>> call = apiService.submitFileLecture(filePart, projectCode, username);
        call.enqueue(new Callback<APIResponse<FileDTO>>() {
            @Override
            public void onResponse(Call<APIResponse<FileDTO>> call, Response<APIResponse<FileDTO>> response) {
                if (response.isSuccessful()) {
                    DialogHelper.showDialog(ChiTietDTDaLamActivity.this, // Context của Activity hiện tại
                            "Thông báo", // Tiêu đề của dialog
                            response.body().getMessage(), // Nội dung của dialog
                            "OK", // Text của nút Positive
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý sự kiện khi người dùng nhấn nút Positive
                                    dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                                }
                            });
                } else {
                    DialogHelper.showDialog(ChiTietDTDaLamActivity.this, // Context của Activity hiện tại
                            "Thông báo", // Tiêu đề của dialog
                            response.body().getMessage(), // Nội dung của dialog
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

            @Override
            public void onFailure(Call<APIResponse<FileDTO>> call, Throwable t) {
                Log.e("FileUpload", "Failed to upload file: " + t.getMessage());
                // Xử lý lỗi
            }
        });
    }

}