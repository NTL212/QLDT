package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.R;

import java.util.List;

public class DeTaiAdapter extends RecyclerView.Adapter<DeTaiAdapter.DeTaiViewHolder> {
    Context context;
    List<DeTai> listDeTai;

    public DeTaiAdapter(Context context, List<DeTai> listDeTai) {
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
        DeTai deTaiModel = listDeTai.get(position);
        holder.tvMaDeTai.setText(deTaiModel.getMaDeTai());
        holder.tvTenDeTai.setText(deTaiModel.getTenDeTai());
        holder.tvMaChuDe.setText(deTaiModel.getTenChuDe());
        holder.tvTinhTrang.setText(deTaiModel.getTinhTrang());
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
                    if (itemView.getContext() instanceof PheDuyetDeTaiActivity){
                        Toast.makeText(context, "Day la view nam trong trang phe duyet de tai", Toast.LENGTH_SHORT).show();
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(context, ChiTietDTPheDuyetActivity.class);
                            intent.putExtra("maDeTai", listDeTai.get(position).getMaDeTai());
                            intent.putExtra("tenDeTai", listDeTai.get(position).getTenDeTai());
                            intent.putExtra("tenChuDe", listDeTai.get(position).getTenChuDe());
                            intent.putExtra("tinhTrang", listDeTai.get(position).getTinhTrang());
                            context.startActivity(intent);
                        }

                    }
                    else if (itemView.getContext() instanceof QuanLyDeTaiActivity){
                        Toast.makeText(context, "Day la view nam trong trang quan ly de tai", Toast.LENGTH_SHORT).show();
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Intent intent = new Intent(context, ChiTietDTQuanLyActivity.class);
                            intent.putExtra("maDeTai", listDeTai.get(position).getMaDeTai());
                            intent.putExtra("tenDeTai", listDeTai.get(position).getTenDeTai());
                            intent.putExtra("tenChuDe", listDeTai.get(position).getTenChuDe());
                            intent.putExtra("tinhTrang", listDeTai.get(position).getTinhTrang());
                            context.startActivity(intent);
                        }
                    }

                }
            });
        }
    }
}