package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDTDaLamActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ChiTietDeTaiActivity;
import com.nhom7.appqldt.Activitys.GiangVien.ListDeTaiActivity;
import com.nhom7.appqldt.Models.DeTai;

import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.List;

public class DeTaiDaLamAdapter extends RecyclerView.Adapter<DeTaiDaLamAdapter.ViewHolder> {

    List<ProjectDTO> listProject;
    Context context;
    public DeTaiDaLamAdapter(List<ProjectDTO> listProject, Context context){
        this.listProject = listProject;
        this.context = context;
    }


    @NonNull
    @Override
    public DeTaiDaLamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_detai_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeTaiDaLamAdapter.ViewHolder holder, int position) {
        ProjectDTO project = this.listProject.get(position);
        holder.tvMaDeTai.setText((CharSequence) project.getId());
        holder.tvTenDeTai.setText((CharSequence) project.getName());
        holder.tvChuDe.setText((CharSequence) project.getTopic().getName());
        holder.tvTinhTrang.setText((CharSequence) project.getStatusForLecturer());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietDTDaLamActivity.class);
                intent.putExtra("projectCode", project.getId()); // Gắn dữ liệu vào Intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProject.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvMaDeTai, tvTenDeTai,  tvChuDe, tvTinhTrang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDeTai = itemView.findViewById(R.id.tvMaDeTai);
            tvTenDeTai = itemView.findViewById(R.id.tvTenDeTai);
            tvChuDe = itemView.findViewById(R.id.tvChuDe);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
        }
    }
}
