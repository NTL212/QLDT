package com.nhom7.appqldt.Activitys.GiangVien;

import static com.nhom7.appqldt.R.layout.activity_chi_tiet_dtda_lam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.FirebaseUpload;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietDTDaLamActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST_CODE = 1;
    TextView tvChiTietMaDeTai, tvChiTietTenDeTai, tvChiTietNguoiDang, tvChiTietChuDe, tvChiTietNgayDang, tvChiTietNgayMoDang, tvChiTietNgayKetThucDang, getTvChiTietFileBaoCaoURL, tvDangTai;
    TextView tvChiTietNgayBatDau, tvChiTietNgayKetThuc, tvChiTietNgayNghiemThu, tvChiTietKinhPhi, tvChiTieSoThanhVien, tvChiTietMoTa, tvChiTietFileDinhKem, tvChiTietFileBaoCao,tvChiTietKetQua,tvChiTietHoiDong, tvChiTietNhanXet  ;
    Button btnChiTietFileBaoCao,btnNopBaoCao, btnXemThanhVien;
    String projectCode;
    String username;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    byte[] data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_chi_tiet_dtda_lam);

        storageReference = FirebaseStorage.getInstance().getReference("uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("uploads");

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
            String status =intent.getStringExtra("status");
            if(!status.contains("Đang thực hiện")){
                btnNopBaoCao.setVisibility(View.GONE);
            }
            projectCode = intent.getStringExtra("projectCode"); // Lấy dữ liệu từ Intent
            // Xử lý dữ liệu ở đây
            getFileModelFromFirebase(projectCode+"_"+username);

            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<Project>> call = apiService.getProjectDetailForLecturer(projectCode);
            call.enqueue(new Callback<APIResponse<Project>>() {
                @Override
                public void onResponse(Call<APIResponse<Project>> call, Response<APIResponse<Project>> response) {
                    if(response.isSuccessful()){

                        Project project = response.body().getResult();
                        DecimalFormat decimalFormat = new DecimalFormat("#");
                        tvChiTietMaDeTai.setText(project.getProjectCode());
                        tvChiTietTenDeTai.setText(project.getName());
                        tvChiTietChuDe.setText(project.getTopic().getName());
                        tvChiTietNgayDang.setText(project.getCreateDate());
                        tvChiTietNgayKetThucDang.setText(project.getCloseRegDate());
                        tvChiTietNgayBatDau.setText(project.getStartDate());
                        tvChiTietNgayKetThuc.setText(project.getEndDate());
                        tvChiTietNgayNghiemThu.setText(project.getAcceptanceDate());
                        tvChiTietKinhPhi.setText(decimalFormat.format(project.getEstBudget()) +" VNĐ");
                        tvChiTieSoThanhVien.setText(String.valueOf(project.getMaxMember()));
                        tvChiTietNguoiDang.setText(project.getLecturer().getName());
                        tvChiTietNgayMoDang.setText(project.getOpenRegDate());
                        tvChiTietMoTa.setText(project.getDescription());

                        btnXemThanhVien.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ListThanhVienDialog dialogFragment = new ListThanhVienDialog(ChiTietDTDaLamActivity.this, projectCode, null, project.getMaxMember());
                                dialogFragment.show(getSupportFragmentManager(), "dialog_fragment_tag");
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<Project>> call, Throwable t) {

                }
            });
        }

        tvChiTietFileBaoCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvChiTietFileBaoCao.getText().toString().contains("Không có file")){
                    return;
                }else {
                    String url = getTvChiTietFileBaoCaoURL.getText().toString();
                    String name = tvChiTietFileBaoCao.getText().toString();
                    downloadAndSaveFile(url, name);
                }
            }
        });



        btnChiTietFileBaoCao.setVisibility(View.GONE);
        tvChiTietFileBaoCao.setVisibility(View.VISIBLE);

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
        tvChiTietFileBaoCao = (TextView) findViewById(R.id.tvChiTietFileBaoCao);
        btnChiTietFileBaoCao = (Button) findViewById(R.id.btnChiTietFileBaoCao);
        btnNopBaoCao = (Button) findViewById(R.id.btnNopBaoCao);
        btnXemThanhVien = (Button) findViewById(R.id.btnXemThanhVien);
        getTvChiTietFileBaoCaoURL = (TextView) findViewById(R.id.tvChiTietFileBaoCaoURL);
        tvDangTai = (TextView) findViewById(R.id.tvDangTai);
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
                    uploadFileToFirebase(fileUri, projectCode, username);
                } else {
                    Toast.makeText(this, "Không thể truy cập tệp tin đã chọn", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Không có tệp tin nào được chọn", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadFileToFirebase(Uri fileUri, String projectCode, String lectCode) {
        if (fileUri != null) {
            // Tạo một tham chiếu tới tệp trên Firebase Storage
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(fileUri));

            // Tải tệp lên Firebase Storage
            fileReference.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Lấy URL của tệp đã tải lên
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String[] parts = fileReference.getName().split("\\.");
                                    // Tạo một đối tượng chứa thông tin về tệp
                                    FirebaseUpload fileModel = new FirebaseUpload();
                                    String id = projectCode + "_" + lectCode;
                                    fileModel.setProjectCode(projectCode);
                                    fileModel.setLectCode(lectCode);
                                    fileModel.setDate(String.valueOf(new Date()));
                                    fileModel.setFilename(projectCode+"Report." + parts[1]); // Đặt tên tệp
                                    fileModel.setUrl(uri.toString()); // Đặt URL tệp

                                    // Gửi đối tượng FileModel lên Firebase Realtime Database
                                    databaseReference.child(id).setValue(fileModel)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(ChiTietDTDaLamActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ChiTietDTDaLamActivity.this, "Failed to upload file information", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ChiTietDTDaLamActivity.this, "Failed to upload file", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void getFileModelFromFirebase(String fileId) {
        // Tạo một DatabaseReference tới nút chứa thông tin về file với id tương ứng
        DatabaseReference fileRef = databaseReference.child(fileId);
        tvChiTietFileBaoCao.setText("Không có file");
        tvChiTietFileBaoCao.setVisibility(View.VISIBLE);
        // Lắng nghe sự kiện khi có dữ liệu thay đổi trên nút đó
        fileRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Kiểm tra xem có dữ liệu không
                if (snapshot.exists()) {
                    // Lấy dữ liệu từ DataSnapshot và tạo đối tượng FileModel
                    FirebaseUpload fileModel = snapshot.getValue(FirebaseUpload.class);

                    // Xử lý khi đã nhận được đối tượng FileModel
                    if (fileModel != null) {
                        // Đã nhận được đối tượng FileModel từ Firebase, bạn có thể sử dụng nó ở đây
                        // Ví dụ:
                        String filename = fileModel.getFilename();
                        String url = fileModel.getUrl();
                        tvChiTietFileBaoCao.setText(filename);
                        tvChiTietFileBaoCao.setVisibility(View.VISIBLE);
                        getTvChiTietFileBaoCaoURL.setText(url);
                    }
                } else {
                    tvChiTietFileBaoCao.setText("Không có file");
                    tvChiTietFileBaoCao.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu từ Firebase
            }
        });
    }


    private void downloadAndSaveFile(String fileUrl, String filename) {
        tvDangTai.setVisibility(View.VISIBLE);
        // Kiểm tra xem URL tệp có hợp lệ không
        if (fileUrl != null && !fileUrl.isEmpty()) {
            // Tạo một tham chiếu tới Firebase Storage với URL tệp
            StorageReference fileRef = FirebaseStorage.getInstance().getReferenceFromUrl(fileUrl);
            if(fileRef==null){
                Log.e("null", "null");
            }else {
                Log.e("null", "khong null");
            }
            // Tạo một tệp tạm thời để lưu trữ tệp đã tải về
            File localFile = null;
            try {
                // Tạo đường dẫn cho tệp tạm thời trong thư mục Downloads với tên tệp là "downloaded_file"
                localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename);

                // Tạo một luồng đầu ra để ghi dữ liệu từ Firebase Storage vào tệp tạm thời
                FileOutputStream outputStream = new FileOutputStream(localFile);
                // Tải tệp từ Firebase Storage và ghi dữ liệu vào tệp tạm thời
                fileRef.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Đóng luồng đầu ra sau khi ghi xong
                                try {
                                    tvDangTai.setVisibility(View.GONE);
                                    outputStream.close();
                                    DialogHelper.showSuccessDialog(ChiTietDTDaLamActivity.this, "Tải file thành công");
                                    // Hiển thị thông báo khi tệp đã được lưu thành công
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi có lỗi xảy ra trong quá trình tải tệp
                                Log.e("that bai", "that bai");
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(ChiTietDTDaLamActivity.this, "Failed to create file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid file URL", Toast.LENGTH_SHORT).show();
        }
    }

}