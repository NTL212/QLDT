package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.fragment.app.Fragment;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThemDeTaiDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThemDeTaiDialogFragment extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    EditText maDeTai, tenDeTai,ngayMoDangKy, ngayKetThucDangKy, ngayNghiemThu, ngayHetHan,kinhPhiDuKien,soLuongThanhVienToiDa;
    Spinner spinnerChuDe;

    Button btnThemDeTai;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThemDeTaiDialogFragment() {
    }

    public static ThemDeTaiDialogFragment newInstance(String param1, String param2) {
        ThemDeTaiDialogFragment fragment = new ThemDeTaiDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.dialogfragment_them_de_tai, container, false);
        maDeTai = view.findViewById(R.id.editTextMaDeTai);
        tenDeTai = view.findViewById(R.id.editTextTenDeTai);
        ngayMoDangKy = view.findViewById(R.id.editTextNgayMoDangKy);
        ngayKetThucDangKy = view.findViewById(R.id.editTextNgayKetThucDangKy);

        ngayNghiemThu = view.findViewById(R.id.editTextNgayNghiemThu);
        ngayHetHan = view.findViewById(R.id.editTextNgayHetHan);
        kinhPhiDuKien = view.findViewById(R.id.editTextKinhPhiDuKien);
        soLuongThanhVienToiDa = view.findViewById(R.id.editTextSoLuongTVToiDa);
        spinnerChuDe = view.findViewById(R.id.spinnerChuDe);
        btnThemDeTai = view.findViewById(R.id.buttonThemDeTai);
//        add data spinnerChuDe

        List<Topic> list = new ArrayList<>();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Topic>>> call = apiService.getAllTopic();
        call.enqueue(new retrofit2.Callback<APIResponse<List<Topic>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Topic>>> call, retrofit2.Response<APIResponse<List<Topic>>> response) {
                if(response.isSuccessful()){
                    list.addAll(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Topic>>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayAdapter<Topic> adapter = new ArrayAdapter<Topic>(this.getContext(), R.layout.simple_topic,list){
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
        spinnerChuDe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Topic selectedA = list.get(position);
                String maChuDe = selectedA.getTopicCode();
                Toast.makeText(getContext(), "Selected id: " + maChuDe, Toast.LENGTH_SHORT).show();
//                set text
                spinnerChuDe.setSelection(position);
                // Xử lý id được chọn ở đây
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có mục nào được chọn
            }
        });
        spinnerChuDe.setAdapter(adapter);

//        findViewById(R.id.button).setOnClickListener(v -> {
//            int selectedPosition = spinnerChuDe.getSelectedItemPosition();
//            A selectedA = list.get(selectedPosition);
//            int selectedId = selectedA.getId();
//            Toast.makeText(MainActivity.this, "Selected id: " + selectedId, Toast.LENGTH_SHORT).show();
//            // Xử lý id được chọn ở đây
//        });




























        btnThemDeTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maDeTaiStr = maDeTai.getText().toString();
                String tenDeTaiStr = tenDeTai.getText().toString();
                String ngayMoDangKyStr = ngayMoDangKy.getText().toString();
                String ngayKetThucDangKyStr = ngayKetThucDangKy.getText().toString();
                String ngayNghiemThuStr = ngayNghiemThu.getText().toString();
                String ngayHetHanStr = ngayHetHan.getText().toString();
                String kinhPhiDuKienStr = kinhPhiDuKien.getText().toString();
                String soLuongThanhVienToiDaStr = soLuongThanhVienToiDa.getText().toString();
                String chuDeStr = spinnerChuDe.getSelectedItem().toString();


                QuanLyDeTaiActivity quanLyDeTaiActivity = (QuanLyDeTaiActivity) getActivity();
                quanLyDeTaiActivity.listDeTai.add(new DeTai("1adada","a232e","b","c"));


                quanLyDeTaiActivity.deTaiAdapter.notifyDataSetChanged();
                dismiss();
            }
        });
        return view;
    }
}