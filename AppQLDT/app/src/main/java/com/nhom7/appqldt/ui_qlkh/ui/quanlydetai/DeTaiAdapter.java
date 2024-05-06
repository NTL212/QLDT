package com.nhom7.appqldt.ui_qlkh.ui.quanlydetai;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.R;

import java.util.List;

public class DeTaiAdapter extends RecyclerView.Adapter<DeTaiAdapter.DeTaiViewHolder> {
    Context context;
    List<DeTaiModel> listDeTai;

    public DeTaiAdapter(Context context, List<DeTaiModel> listDeTai) {
        this.context = context;
        this.listDeTai = listDeTai;
    }

    @NonNull
    @Override
    public DeTaiAdapter.DeTaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_detai, null);
        DeTaiViewHolder deTaiViewHolder = new DeTaiViewHolder(view);
        return deTaiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeTaiAdapter.DeTaiViewHolder holder, int position) {
        DeTaiModel deTaiModel = listDeTai.get(position);
        holder.tvMaDeTai.setText(deTaiModel.getMaDeTai());
        holder.tvTenDeTai.setText(deTaiModel.getTenDeTai());
        holder.tvMaChuDe.setText(deTaiModel.getMaChuDe());
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
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Toast.makeText(context, "Click item " + position, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
