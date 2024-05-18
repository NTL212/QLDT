package com.nhom7.appqldt.Adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.QuanLy.ListChuDeActivity;
import com.nhom7.appqldt.Activitys.QuanLy.UpdateChuDeDialog;
import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;

import java.util.List;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder> {
    public interface OnEditButtonClickListener {
        void onEditButtonClick(int position);
    }
    Context context;
    List<Topic> chuDeList;
//    private OnEditButtonClickListener editButtonClickListener;
//
//    public void setOnEditButtonClickListener(OnEditButtonClickListener listener) {
//        this.editButtonClickListener = listener;
//    }
    public ChuDeAdapter(Context context, List<Topic> chuDeList) {
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
        Topic chuDe = chuDeList.get(position);
        Log.d("onBindViewHolder: ", chuDeList.get(position).getName());

        holder.tvstt.setText(String.valueOf(position + 1));
        holder.tvTenChuDe.setText(chuDeList.get(position).getName());
//        holder.tvSoLuongDeTai.setText(String.valueOf(chuDeList.get(position).getSoLuongDeTai()));
        holder.tvDangMoDangKy.setText(chuDeList.get(position).isEnabled() ? "Đang mở đăng ký" : "Đã đóng đăng ký");

    }

    @Override
    public int getItemCount() {
        return chuDeList == null ? 0 : chuDeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvstt, tvTenChuDe, tvSoLuongDeTai, tvDangMoDangKy;
        ImageButton btnEdit, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvstt = itemView.findViewById(R.id.tvStt);
            tvTenChuDe = itemView.findViewById(R.id.tvChude);
            tvSoLuongDeTai = itemView.findViewById(R.id.tvSoluongdetai);
            tvDangMoDangKy = itemView.findViewById(R.id.tvDangmodangky);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnRemove = itemView.findViewById(R.id.btnRemove);

            btnEdit.setOnClickListener(e -> {
                ListChuDeActivity activity = (ListChuDeActivity) context;
                activity.showeditChuDe(chuDeList.get(getAdapterPosition()));

            });
            btnRemove.setOnClickListener(e -> {
                ListChuDeActivity activity = (ListChuDeActivity) context;
                activity.removeChuDe(chuDeList.get(getAdapterPosition()));
            });

        }
    }
}
