package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemDeTaiDiaLog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemDeTaiDiaLog extends DialogFragment {

    public ThemDeTaiDiaLog() {
    }


    public static ThemDeTaiDiaLog newInstance(String param1, String param2) {
        ThemDeTaiDiaLog fragment = new ThemDeTaiDiaLog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    EditText tvMaDeTai, tvTenDeTai, tvngaymodangky, tvngayketthucdangky, tvngaybatdau, tvngayketthuc, tvngaynghiemthu,
            tvngansach, tvsoluongtvmax;
    Spinner spnChude;
    List<Topic> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_de_tai_dia_log, container, false);
        tvMaDeTai = view.findViewById(R.id.editTextMaDeTai);
        tvTenDeTai = view.findViewById(R.id.editTextTenDeTai);
        tvngaymodangky = view.findViewById(R.id.editTextNgayMoDangKy);
        tvngayketthucdangky = view.findViewById(R.id.editTextNgayKetThucDangKy);
        tvngaybatdau = view.findViewById(R.id.editTextNgayBatDau);
        tvngayketthuc = view.findViewById(R.id.editTextNgayHetHan);
        tvngaynghiemthu = view.findViewById(R.id.editTextNgayNghiemThu);
        tvngansach = view.findViewById(R.id.editTextKinhPhiDuKien);
        tvsoluongtvmax = view.findViewById(R.id.editTextSoLuongTVToiDa);
        spnChude = view.findViewById(R.id.spinnerChuDe);

        loadintoSpinChude();
        view.findViewById(R.id.buttonThemDeTai).setOnClickListener(v -> {
            String datenow= "2024-01-01";
            String maDeTai = tvMaDeTai.getText().toString();
            String tenDeTai = tvTenDeTai.getText().toString();
            String ngayMoDangKy = tvngaymodangky.getText().toString().equals("") ? datenow:tvngaymodangky.getText().toString();
            String ngayKetThucDangKy = tvngayketthucdangky.getText().toString().equals("") ? datenow:tvngayketthucdangky.getText().toString();

            String ngayBatDau = tvngaybatdau.getText().toString().equals("") ? datenow:tvngaybatdau.getText().toString();
            String ngayKetThuc = tvngayketthuc.getText().toString().equals("") ? datenow:tvngayketthuc.getText().toString();
            String ngayNghiemThu = tvngaynghiemthu.getText().toString().equals("") ? datenow:tvngaynghiemthu.getText().toString();
            double nganSach=0;
            int soLuongTVMax=0;
            try {
                nganSach = Double.parseDouble(tvngansach.getText().toString());
                soLuongTVMax = Integer.parseInt(tvsoluongtvmax.getText().toString());
            }
            catch (Exception e){
                Toast.makeText(this.getContext(), "Nhập số hop le vào kinh phí hoặc số lượng thành viên", Toast.LENGTH_SHORT).show();
                return;
            }

            Topic topic = (Topic) spnChude.getSelectedItem();
            Intent intent = new Intent();
            //neu nhap ngay hop le yyyy-mm-dd
            if (ngayMoDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThucDangKy.matches("\\d{4}-\\d{2}-\\d{2}") && ngayBatDau.matches("\\d{4}-\\d{2}-\\d{2}") && ngayKetThuc.matches("\\d{4}-\\d{2}-\\d{2}") && ngayNghiemThu.matches("\\d{4}-\\d{2}-\\d{2}")) {
                Project project = new Project(maDeTai,tenDeTai,null,"",soLuongTVMax,ngayMoDangKy,ngayKetThucDangKy,ngayBatDau,ngayKetThuc,ngayNghiemThu,nganSach,null,topic,null,true);
                QuanLyDeTaiActivity activity = (QuanLyDeTaiActivity) getActivity();
                activity.addDeTai(project);
                dismiss();
            } else {
                Toast.makeText(this.getContext(), "Nhập ngày theo định dạng yyyy-mm-dd", Toast.LENGTH_SHORT).show();
                return;
            }


        });
        return view;

    }


    void loadintoSpinChude() {
        list = new ArrayList<>();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllTopic().enqueue(new retrofit2.Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(retrofit2.Call<APIResponse<List<Topic>>> call, retrofit2.Response<APIResponse<List<Topic>>> response) {
                if (response.body().isSuccess()) {
                    list = response.body().getResult();


                    ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(getContext(), R.layout.simple_topic, list) {
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
//                            Topic selectedA = (Topic) parent.getItemAtPosition(position);
//                            String selectedId = selectedA.getTopicCode();
//                            Toast.makeText(QuanLyDeTaiActivity, "Selected ID: " + selectedId, Toast.LENGTH_SHORT).show();
                            // Xử lý id được chọn ở đây
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // Xử lý khi không có mục nào được chọn
                        }
                    });

                    spnChude.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<APIResponse<List<Topic>>> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}