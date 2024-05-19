package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.Admin.ChiTietGiangVienAdminActivity;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.R;

import java.util.List;

public class GiangVienAdapter extends RecyclerView.Adapter<GiangVienAdapter.ViewHolder> {

    List<LecturerAccount> giangvienList;
    Context context;


    public GiangVienAdapter(List<LecturerAccount> giangvienList, Context context) {
        this.giangvienList = giangvienList;
        this.context = context;
    }

    public GiangVienAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_giangvien_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LecturerAccount lecturerAccount = this.giangvienList.get(position);
        holder.tvtenkhoa.setText("Tên khoa : " + lecturerAccount.getLecturer().getFaculty().getName());
        holder.tvmagv.setText(lecturerAccount.getLecturer().getLecturerCode());
        holder.tvten.setText("Họ Tên : " + lecturerAccount.getLecturer().getName());
        holder.tvngaysinh.setText("Ngày sinh : " + lecturerAccount.getLecturer().getBirthday());
        holder.tvsdt.setText("Số điện thoại : " + lecturerAccount.getLecturer().getPhoneNum());
        holder.tvgioitinh.setText("Giới tính : " + lecturerAccount.getLecturer().getSex());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lecturerCode",lecturerAccount.getLecturer().getLecturerCode());
                Intent intent = new Intent(context, ChiTietGiangVienAdminActivity.class);
                intent.putExtra("lecturerCode", lecturerAccount.getLecturer().getLecturerCode());// Gắn dữ liệu vào Intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return giangvienList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvmagv, tvten, tvngaysinh, tvsdt, tvgioitinh, tvtenkhoa;
        public ViewHolder(@NonNull View itemView ){
            super(itemView);
            tvmagv = itemView.findViewById(R.id.tv_DSGV_Admin_MaGV);
            tvten = itemView.findViewById(R.id.tv_DSGV_Admin_HoTen);
            tvngaysinh = itemView.findViewById(R.id.tv_DSGV_Admin_NamSinh);
            tvsdt = itemView.findViewById(R.id.tv_DSGV_Admin_SDT);
            tvgioitinh = itemView.findViewById(R.id.tv_DSGV_Admin_GioiTinh);
            tvtenkhoa = itemView.findViewById(R.id.tv_DSGV_Admin_Khoa);

        }
    }
}
