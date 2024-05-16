package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.List;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder>{
    Context context;
    List<ChuDe> chuDeList;

    public ChuDeAdapter(Context context, List<ChuDe> chuDeList) {
        this.context = context;
        this.chuDeList = chuDeList;
    }

    @NonNull
    @Override
    public ChuDeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_chude_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuDeAdapter.ViewHolder holder, int position) {
        ChuDe chuDe = chuDeList.get(position);
        Log.d( "onBindViewHolder: ", chuDeList.get(position).getTenChuDe());

        holder.tvstt.setText(String.valueOf(position+1));
        holder.tvTenChuDe.setText(chuDeList.get(position).getTenChuDe());
        holder.tvSoLuongDeTai.setText(String.valueOf(chuDeList.get(position).getSoLuongDeTai()));
        holder.tvDangMoDangKy.setText(chuDeList.get(position).isDangMoDangKy() ? "Đang mở đăng ký" : "Đã đóng đăng ký");

    }

    @Override
    public int getItemCount() {
        return chuDeList == null ? 0 : chuDeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvstt, tvTenChuDe, tvSoLuongDeTai, tvDangMoDangKy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvstt = itemView.findViewById(R.id.tvStt);
            tvTenChuDe = itemView.findViewById(R.id.tvChude);
            tvSoLuongDeTai = itemView.findViewById(R.id.tvSoluongdetai);
            tvDangMoDangKy = itemView.findViewById(R.id.tvDangmodangky);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }
    }
}
