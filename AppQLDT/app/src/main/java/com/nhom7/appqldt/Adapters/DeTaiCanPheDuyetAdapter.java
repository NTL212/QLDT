package com.nhom7.appqldt.Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.QuanLy.ChiTietDTPheDuyetActivity;
import com.nhom7.appqldt.Models.DeTaiCanPheDuyet;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.List;

public class DeTaiCanPheDuyetAdapter extends RecyclerView.Adapter<DeTaiCanPheDuyetAdapter.DeTaiCanPheDuyetViewHolder> {
    Context context;
    List<DeTaiCanPheDuyet> listDeTai;

    public DeTaiCanPheDuyetAdapter(Context context, List<DeTaiCanPheDuyet> listDeTai) {
        this.context = context;
        this.listDeTai = listDeTai;
    }

    @NonNull
    @Override
    public DeTaiCanPheDuyetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.list_dt_can_phe_duyet_item, null);
        DeTaiCanPheDuyetViewHolder deTaiViewHolder = new DeTaiCanPheDuyetViewHolder(view);
        return deTaiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DeTaiCanPheDuyetViewHolder holder, int position) {
        DeTaiCanPheDuyet deTaiModel = listDeTai.get(position);
        holder.tvMaDeTai.setText(deTaiModel.getId());
        holder.tvTenDeTai.setText(deTaiModel.getName());
        if (deTaiModel.getTopic() != null)
            holder.tvMaChuDe.setText(deTaiModel.getTopic().getName());
        else
            holder.tvMaChuDe.setText("Chưa có chủ đề");
        if (deTaiModel.getLecturer() != null)
            holder.tvLecture.setText(deTaiModel.getLecturer().getName());
        else
            holder.tvLecture.setText("No lecture");
//        holder.tvTinhTrang.setText(deTaiModel.isStatus() ? "Dang mo dang ky" : "Da dong");
        holder.tvTinhTrang.setText(deTaiModel.getStatus());
        //set onclick

    }

    @Override
    public int getItemCount() {
        return listDeTai.size();
    }

    public class DeTaiCanPheDuyetViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDeTai, tvTenDeTai, tvMaChuDe, tvLecture,tvTinhTrang;

        public DeTaiCanPheDuyetViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDeTai = itemView.findViewById(R.id.tvMaDeTai);
            tvTenDeTai = itemView.findViewById(R.id.tvTenDeTai);
            tvMaChuDe = itemView.findViewById(R.id.tvChuDe);
            tvLecture = itemView.findViewById(R.id.tvLecture);
            tvTinhTrang = itemView.findViewById(R.id.tvTinhTrang);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChiTietDTPheDuyetActivity.class);
                    intent.putExtra("maDeTai", listDeTai.get(getAdapterPosition()).getId());
                    intent.putExtra("nameLec", listDeTai.get(getAdapterPosition()).getLecturer().getName());
                    intent.putExtra("LecturerCode", listDeTai.get(getAdapterPosition()).getLecturer().getId());
                    Log.e("TAG", "onClick: " + listDeTai.get(getAdapterPosition()).getLecturer().getName());
                    Log.e("TAG", "onClick: " + listDeTai.get(getAdapterPosition()).getLecturer().getId() );
                    context.startActivity(intent);

                }
            });
        }
    }
}
