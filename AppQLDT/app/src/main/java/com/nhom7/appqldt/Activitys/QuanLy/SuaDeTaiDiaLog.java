package com.nhom7.appqldt.Activitys.QuanLy;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
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
import androidx.fragment.app.DialogFragment;


import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.ProjectChiTietQL;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SuaDeTaiDiaLog extends DialogFragment {


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

    public SuaDeTaiDiaLog() {
        // Required empty public constructor
    }

    public static SuaDeTaiDiaLog newInstance(String param1, String param2) {
        SuaDeTaiDiaLog fragment = new SuaDeTaiDiaLog();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    EditText maDeTai, tenDeTai,ngayMoDangKy, ngayKetThucDangKy, ngayNghiemThu, editTextNgayBatDau,ngayHetHan,kinhPhiDuKien,soLuongThanhVienToiDa;
    Button btnsuaDeTai;
    Spinner spnChude;
    List<Topic> list ;
    ProjectChiTietQL pro;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_de_tai_dia_log, container, false);
//        maDeTai.setText(getIntent().getStringExtra("maDeTai"));
        maDeTai = (EditText) view.findViewById(R.id.editTextMaDeTai);
        tenDeTai =view. findViewById(R.id.editTextTenDeTai);
        spnChude = view.findViewById(R.id.spinnerCD);
        ngayMoDangKy =view. findViewById(R.id.editTextNgayMoDangKy);
        editTextNgayBatDau = view.findViewById(R.id.editTextNgayBatDau);
        ngayKetThucDangKy = view.findViewById(R.id.editTextNgayKetThucDangKy);
        ngayNghiemThu =view. findViewById(R.id.editTextNgayNghiemThu);
        ngayHetHan = view.findViewById(R.id.editTextNgayHetHan);
        kinhPhiDuKien = view.findViewById(R.id.editTextKinhPhiDuKien);
        soLuongThanhVienToiDa =view. findViewById(R.id.editTextSoLuongTVToiDa);
        btnsuaDeTai = view.findViewById(R.id.btnSuaDeTai);
        loadintoSpinChude();
        displayProject(pro);


        btnsuaDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDeTai_str = maDeTai.getText().toString();
                String tenDeTai_str = tenDeTai.getText().toString();
                String ngayMoDangKy_str = ngayMoDangKy.getText().toString();
                String ngayKetThucDangKy_str = ngayKetThucDangKy.getText().toString();

                String ngayNghiemThu_str = ngayNghiemThu.getText().toString();
                String ngayBatDau_str = editTextNgayBatDau.getText().toString();
                String ngayHetHan_str = ngayHetHan.getText().toString();
                double kinhPhiDuKien_db = 0;
                int soLuongThanhVienToiDa_int = 0;
                try {
                    kinhPhiDuKien_db = Double.parseDouble(kinhPhiDuKien.getText().toString());
                    soLuongThanhVienToiDa_int = Integer.parseInt(soLuongThanhVienToiDa.getText().toString());
                }
                catch (Exception e){
                    Toast.makeText(getContext(), "Nhập số hop le vào kinh phí hoặc số lượng thành viên", Toast.LENGTH_SHORT).show();
                    return;
                }

                Topic tp = ((Topic) spnChude.getSelectedItem());
                String datenow= "2024-01-01";
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    datenow= LocalDate.now().toString();
                }

                if (ngayMoDangKy_str.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThucDangKy_str.matches("\\d{4}-\\d{2}-\\d{2}") && ngayBatDau_str.matches("\\d{4}-\\d{2}-\\d{2}") && ngayHetHan_str.matches("\\d{4}-\\d{2}-\\d{2}") && ngayNghiemThu_str.matches("\\d{4}-\\d{2}-\\d{2}")) {

                } else {
                    Toast.makeText(getContext(), "Nhập ngày theo định dạng yyyy-mm-dd", Toast.LENGTH_SHORT).show();
                    return;
                }
                Project project = new Project(maDeTai_str,tenDeTai_str,datenow,null,soLuongThanhVienToiDa_int,ngayMoDangKy_str,ngayKetThucDangKy_str,ngayBatDau_str,ngayHetHan_str,ngayNghiemThu_str,kinhPhiDuKien_db,null,tp,null,false);
//                APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
//                apiService.updateProject(project).enqueue(new retrofit2.Callback<APIResponse<Project>>() {
//                    @Override
//                    public void onResponse(retrofit2.Call<APIResponse<Project>> call, retrofit2.Response<APIResponse<Project>> response) {
//                        if (response.body().isSuccess()) {
//                            Toast.makeText(SuaDeTaiActivity.this, "Sửa đề tài thành công", Toast.LENGTH_SHORT).show();
//
//
//
//                        } else {
//                            Toast.makeText(SuaDeTaiActivity.this, "Sửa đề tài thất bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<APIResponse<Project>> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
                ChiTietDTQuanLyActivity chiTietDTQuanLyActivity = (ChiTietDTQuanLyActivity) getActivity();
                chiTietDTQuanLyActivity.suadetai(project);
                dismiss();


            }
        });




        return view;
    }
    void setProject(ProjectChiTietQL project){
       this.pro=project;
    }
    void displayProject(ProjectChiTietQL project){
//        Log.e("TAG", "onResponse: " + project );
        maDeTai.setText(project.getProjectCode());
        tenDeTai.setText(project.getName());
        ngayMoDangKy.setText(project.getOpenRegDate());
        ngayKetThucDangKy.setText(project.getCloseRegDate());
        editTextNgayBatDau.setText(project.getStartDate());
        ngayHetHan.setText(project.getEndDate());
        ngayNghiemThu.setText(project.getAcceptanceDate());
        DecimalFormat decimalFormat = new DecimalFormat("#");
        kinhPhiDuKien.setText(decimalFormat.format(project.getEstBudget()));
        soLuongThanhVienToiDa.setText(project.getMaxMember()+"");
        //set spinner
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getTopicCode().equals(project.getTopic().getTopicCode())) {
//                spnChude.setSelection(i);
//                break;
//            }
//            }
    }
    void loadintoSpinChude() {
        list = new ArrayList<>();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTopic().enqueue(new retrofit2.Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<List<Topic>>> call, retrofit2.Response<APIResponse<List<Topic>>> response) {
                if (response.body().isSuccess()) {
                    list = response.body().getResult();



                    ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(getContext(), R.layout.simple_topic,list){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setText(list.get(position).getName());
                            return view;
                        }

                        @Override
                        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView textView = (TextView) view.findViewById(android.R.id.text1);
                            textView.setText(list.get(position).getName());
                            return view;
                        }
                    };

                    spnChude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
                            // Xử lý id được chọn ở đây
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Xử lý khi không có mục nào được chọn
                        }
                    });

                    spnChude.setAdapter(adapter);
                    Log.e("p", "onResponse: " + pro.getTopic().getName() );
                    for (int i = 0; i < list.size(); i++) {
                        Log.e("p2", "onResponse: " + list.get(i).getName() );
                        if (list.get(i).getName().equals(pro.getTopic().getName())) {
                            spnChude.setSelection(i,true);
                            Log.e(TAG, "onResponse: daset" );
                            break;
                        }
                    }

                }
            }
            @Override
            public void onFailure(retrofit2.Call<APIResponse<List<Topic>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}