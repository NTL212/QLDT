package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.List;

public class DeTaiAdapter extends RecyclerView.Adapter<DeTaiAdapter.DeTaiViewHolder> {
    Context context;
    List<Project> listDeTai;

    public DeTaiAdapter(Context context, List<Project> listDeTai) {
        this.context = context;
        this.listDeTai = listDeTai;
    }

    @NonNull
    @Override
    public DeTaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_detai_item, null);
        DeTaiViewHolder deTaiViewHolder = new DeTaiViewHolder(view);
        return deTaiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeTaiViewHolder holder, int position) {
        Project deTaiModel = listDeTai.get(position);
        holder.tvMaDeTai.setText(deTaiModel.getProjectCode());
        holder.tvTenDeTai.setText(deTaiModel.getName());
        if (deTaiModel.getTopic() != null)
            holder.tvMaChuDe.setText(deTaiModel.getTopic().getName());
        else
            holder.tvMaChuDe.setText("Chưa có chủ đề");
        holder.tvTinhTrang.setText(deTaiModel.isProposed() ? "Dang mo dang ky" : "Da dong");
        //set onclick

    }

    @Override
    public int getItemCount() {
        return listDeTai.size();
    }

    public class DeTaiViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDeTai, tvTenDeTai, tvMaChuDe, tvTinhTrang;

        public DeTaiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDeTai = itemView.findViewById(R.id.tvMaDeTai);
            tvTenDeTai = itemView.findViewById(R.id.tvTenDeTai);
            tvMaChuDe = itemView.findViewById(R.id.tvChuDe);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (context instanceof PheDuyetDeTaiActivity){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(context, ChiTietDTPheDuyetActivity.class);
                            intent.putExtra("maDeTai", listDeTai.get(position).getProjectCode());
                            intent.putExtra("tenDeTai", listDeTai.get(position).getName());
                            intent.putExtra("tenChuDe", listDeTai.get(position).getTopic().getName());
                            intent.putExtra("tinhTrang", listDeTai.get(position).isProposed());
                            context.startActivity(intent);
                        }

                    }
                    else if (itemView.getContext() instanceof QuanLyDeTaiActivity){
                        Toast.makeText(context, "Day la view nam trong trang quan ly de tai", Toast.LENGTH_SHORT).show();
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(context, ChiTietDTQuanLyActivity.class);
                            intent.putExtra("maDeTai", listDeTai.get(position).getProjectCode());
                            context.startActivity(intent);
                        }
                    }

                }
            });
        }
    }
}
