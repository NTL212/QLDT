package com.nhom7.appqldt.Activitys.GiangVien;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.Admin.ChinhSuaGiangVienAdminActivity;
import com.nhom7.appqldt.Activitys.Admin.ChinhSuaHocSinhAdminActivity;
import com.nhom7.appqldt.Activitys.Admin.DanhSachGiangVienAdminActivity;
import com.nhom7.appqldt.Activitys.Admin.ThemGiangVienAdminActivity;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Activitys.QuanLy.ChiTietDTQuanLyActivity;
import com.nhom7.appqldt.Helpers.CheckHepler;
import com.nhom7.appqldt.Helpers.DateHelper;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Lecturer;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.ProjectChiTietQL;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuaProfileDiaLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuaProfileDiaLog extends DialogFragment {

    EditText tv_CN_HoTen, tv_CN_GioiTinh, tv_CN_NgaySinh, tv_CN_CCCD, tv_CN_SDT, tv_CN_DiaChi, tv_CN_Email;
    TextView tv_CN_MaKhoa, tv_CN_MaSo, tv_CN_Khoa;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // Loại bỏ tiêu đề của Dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return dialog;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Lấy kích thước hiện tại của cửa sổ
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        // Đặt chiều rộng và chiều cao cho Dialog
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    }

    public SuaProfileDiaLog() {
        // Required empty public constructor
    }

    public static SuaProfileDiaLog newInstance(String param1, String param2) {
        SuaProfileDiaLog fragment = new SuaProfileDiaLog();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    Button btnSuaThongTin;
    Context context;
    Lecturer lecturer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_profile_gv_dia_log, container, false);
//        maDeTai.setText(getIntent().getStringExtra("maDeTai"));
        tv_CN_HoTen = view.findViewById(R.id.tv_CN_HoTen);
        tv_CN_MaSo = view.findViewById(R.id.tv_CN_MaSo);
        tv_CN_CCCD = view.findViewById(R.id.tv_CN_CCCD);
        tv_CN_DiaChi = view.findViewById(R.id.tv_CN_DiaChi);
        tv_CN_Email = view.findViewById(R.id.tv_CN_Email);
        tv_CN_GioiTinh = view.findViewById(R.id.tv_CN_GioiTinh);
        tv_CN_Khoa = view.findViewById(R.id.tv_CN_Khoa);
        tv_CN_NgaySinh = view.findViewById(R.id.tv_CN_NgaySinh);
        tv_CN_SDT = view.findViewById(R.id.tv_CN_SDT);
        btnSuaThongTin = view.findViewById(R.id.btnSuaThongTin);
        tv_CN_MaKhoa = view.findViewById(R.id.tv_CN_MaKhoa);

        displayLecture(lecturer);


        btnSuaThongTin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Xác nhận chỉnh sửa")
                        .setMessage("Bạn có chắc chắn muốn chỉnh sửa thông tin không?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    String hoten = tv_CN_HoTen.getText().toString();
                                    String id = tv_CN_MaSo.getText().toString();
                                    String cccd = tv_CN_CCCD.getText().toString();
                                    String diachi = tv_CN_DiaChi.getText().toString();
                                    String email = tv_CN_Email.getText().toString();
                                    String sex = tv_CN_GioiTinh.getText().toString();
                                    String khoa = tv_CN_MaKhoa.getText().toString();
                                    String ngaysinh = tv_CN_NgaySinh.getText().toString();
                                    String sdt = tv_CN_SDT.getText().toString();
                                    if(CheckHepler.checkCCCD(cccd)){
                                        Toast.makeText(context, "Nhập lại cccd/cmnd 12 số", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if(CheckHepler.checkPhone(sdt)){
                                        Toast.makeText(context, "Nhập lại so dien thoai 10 số", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    if (hoten.isEmpty() || id.isEmpty() || cccd.isEmpty() || diachi.isEmpty() ||
                                            email.isEmpty() || sex.isEmpty() || khoa.isEmpty() || ngaysinh.isEmpty() || sdt.isEmpty()) {
                                        throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin");
                                    }

                                    APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                                    Call<APIResponse<String>> call = apiService.editProfieLecture(id, hoten, DateHelper.convertToStandardDate(ngaysinh), diachi, cccd, sdt, email, sex, khoa);
                                    call.enqueue(new Callback<APIResponse<String>>() {
                                        @Override
                                        public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                                            if (response.body().isSuccess()) {
                                                Toast.makeText(context, "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(context, ThongTinCaNhanActivity.class);
                                                startActivity(intent);

                                            } else {
                                                DialogHelper.showFailedDialog(context, response.body().getMessage());
                                            }
                                            dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                                            DialogHelper.showFailedDialog(context, "Không gọi API được");
                                            dismiss();
                                        }
                                    });
                                } catch (IllegalArgumentException e) {
                                    DialogHelper.showFailedDialog(context, e.getMessage());
                                } catch (Exception e) {
                                    DialogHelper.showFailedDialog(context, "Vui lòng nhập đúng");
                                }

                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();;


            }
        });


        return view;
    }

    void setLecture_Context(Lecturer lecturer, Context context) {
        this.lecturer = lecturer;
        this.context = context;
    }

    void displayLecture(Lecturer lecturer) {
        tv_CN_HoTen.setText(lecturer.getName());
        tv_CN_MaSo.setText(lecturer.getLecturerCode());
        tv_CN_CCCD.setText(lecturer.getIdNum());
        tv_CN_DiaChi.setText(lecturer.getAddress());
        tv_CN_Email.setText(lecturer.getEmail());
        tv_CN_GioiTinh.setText(lecturer.getSex());
        tv_CN_Khoa.setText(lecturer.getFaculty().getName());
        tv_CN_NgaySinh.setText(lecturer.getBirthday());
        tv_CN_SDT.setText(lecturer.getPhoneNum());
        tv_CN_MaKhoa.setText(lecturer.getFaculty().getFalculityCode());
    }
}