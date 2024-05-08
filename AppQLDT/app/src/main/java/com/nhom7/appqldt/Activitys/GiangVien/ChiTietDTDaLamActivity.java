package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.R;

public class ChiTietDTDaLamActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 1;
    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietNguoiDang, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa, tvChiTietFileDinhKem, tvChiTietFileBaoCao,tvChiTietKetQua,tvChiTietHoiDong, tvChiTietNhanXet  ;
    Button btnChiTietFileBaoCao, btnChiTietFileDinhKem;
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
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(sharedPreferences.getString("username",""));

        AnhXa();

        btnChiTietFileBaoCao.setVisibility(View.GONE);
        tvChiTietFileBaoCao.setVisibility(View.VISIBLE);


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
            Uri fileUri = data.getData();
            Log.d("File", fileUri.getPath());
            // Sau khi có Uri, bạn có thể làm bất kỳ điều gì bạn muốn với file này, ví dụ như upload lên máy chủ
            // Ví dụ:
            // File file = new File(getRealPathFromURI(fileUri)); // Lấy đường dẫn thực của file từ Uri
            // FileUploader fileUploader = new FileUploader();
            // fileUploader.uploadFile(file, url);
        }
    }

    // Phương thức để lấy đường dẫn thực của file từ Uri
    private String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) {
            return uri.getPath();
        } else {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
    }

}