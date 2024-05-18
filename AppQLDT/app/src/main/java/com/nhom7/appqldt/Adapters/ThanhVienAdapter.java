package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDTDaLamActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListThanhVienDialog;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.R;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {

    List<StudentDTO> listTV;
    private FragmentManager fragmentManager;
    String projectCode ;
    Context context;
    public ThanhVienAdapter(List<StudentDTO> listTV, Context context, String projectCode,FragmentManager fragmentManager){
        this.listTV = listTV;
        this.context = context;
        this.projectCode = projectCode;
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_thanhvien_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        StudentDTO thanhVien = this.listTV.get(position);
        holder.tvStt.setText((CharSequence)String.valueOf(position));
        holder.tvHoTen.setText((CharSequence) thanhVien.getName());
        holder.tvMSSV.setText((CharSequence) "MSSV: " +  thanhVien.getId());
        holder.tvNgaySinh.setText((CharSequence)thanhVien.getBirthday());
        holder.tvLop.setText((CharSequence)  "Lớp: "  +thanhVien.getClassObj().getName());
        holder.tvKhoa.setText((CharSequence) "Khoa: " +  thanhVien.getFalculity().getName());

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Call<APIResponse<String>> call = apiService.deleteMemberToProject(projectCode,thanhVien.getId());
                call.enqueue(new Callback<APIResponse<String>>() {
                    @Override
                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                        if(response.body().isSuccess()){
                            DialogHelper.showSuccessDialog(context, response.body().getMessage());
                            ListThanhVienDialog dialogFragment = new ListThanhVienDialog(context, projectCode, null, 0);
                            dialogFragment.show(fragmentManager, "dialog_fragment_tag");
                        }else {
                            DialogHelper.showFailedDialog(context, response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                        DialogHelper.showFailedDialog(context, t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvStt, tvHoTen,  tvMSSV, tvNgaySinh, tvLop, tvKhoa;
        Button btnXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_TV_STT);
            tvHoTen = itemView.findViewById(R.id.tv_TV_HoTen);
            tvMSSV = itemView.findViewById(R.id.tv_TV_MSSV);
            tvNgaySinh = itemView.findViewById(R.id.tv_TV_NamSinh);
            tvLop = itemView.findViewById(R.id.tv_TV_Lop);
            tvKhoa = itemView.findViewById(R.id.tv_TV_Khoa);
            btnXoa = itemView.findViewById(R.id.btnXoaThanhVien);
        }
    }
}
