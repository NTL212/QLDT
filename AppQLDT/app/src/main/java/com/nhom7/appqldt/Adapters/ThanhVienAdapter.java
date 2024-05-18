package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder> {

    List<ThanhVien> listTV;
    Context context;
    public ThanhVienAdapter(List<ThanhVien> listTV, Context context){
        this.listTV = listTV;
        this.context = context;
    }


    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_thanhvien_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        ThanhVien thanhVien = this.listTV.get(position);
        holder.tvStt.setText((CharSequence)String.valueOf(thanhVien.getStt()));
        holder.tvHoTen.setText((CharSequence) "Họ tên: " +  thanhVien.getHoTen());
        holder.tvMSSV.setText((CharSequence) "MSSV: " +  thanhVien.getMSSV());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        holder.tvNgaySinh.setText((CharSequence) "Năm sinh: " +  dateFormat.format(thanhVien.getNgaySinh()));
        holder.tvLop.setText((CharSequence)  "Lớp: "  +thanhVien.getLop());
        holder.tvKhoa.setText((CharSequence) "Khoa: " +  thanhVien.getKhoa());
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvStt, tvHoTen,  tvMSSV, tvNgaySinh, tvLop, tvKhoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_TV_STT);
            tvHoTen = itemView.findViewById(R.id.tv_TV_HoTen);
            tvMSSV = itemView.findViewById(R.id.tv_TV_MSSV);
            tvNgaySinh = itemView.findViewById(R.id.tv_TV_NamSinh);
            tvLop = itemView.findViewById(R.id.tv_TV_Lop);
            tvKhoa = itemView.findViewById(R.id.tv_TV_Khoa);
        }
    }
}
