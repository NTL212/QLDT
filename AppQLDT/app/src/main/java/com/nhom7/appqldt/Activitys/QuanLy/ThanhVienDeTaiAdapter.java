package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Models.ThanhVienDeTai;
import com.nhom7.appqldt.R;

import java.util.List;

public class ThanhVienDeTaiAdapter extends RecyclerView.Adapter<ThanhVienDeTaiAdapter.ViewHolder> {
    List<ThanhVienDeTai> listTV;
    Context context;

    public ThanhVienDeTaiAdapter(List<ThanhVienDeTai> listTV, Context context) {
        this.listTV = listTV;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_thanhvien_item_dialogfragment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThanhVienDeTai tv = listTV.get(position);
        holder.tvStt.setText(String.valueOf(position+1));
        holder.tvHoTen.setText(tv.getHoTen());
        holder.tvMSSV.setText(tv.getMSSV());
        holder.tvLop.setText(tv.getLop());
        holder.tvKhoa.setText(tv.getKhoa());
        holder.tvNgayThamGia.setText(tv.getNgayThamGia());
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvStt, tvMSSV , tvHoTen, tvLop, tvKhoa,tvNgayThamGia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_TV_STT);
            tvHoTen = itemView.findViewById(R.id.tv_TV_HoTen);
            tvMSSV = itemView.findViewById(R.id.tv_TV_HoTen);
            tvLop = itemView.findViewById(R.id.tv_TV_Lop);
            tvKhoa = itemView.findViewById(R.id.tv_TV_Khoa);
            tvNgayThamGia = itemView.findViewById(R.id.tv_TV_NgayThamGia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }
}
