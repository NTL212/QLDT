package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.DTO.NguoiNhanDTO;
import com.nhom7.appqldt.Activitys.QuanLy.GuiThongBaoActivity;
import com.nhom7.appqldt.R;

import java.util.List;

public class NguoiNhanAdapter extends RecyclerView.Adapter<NguoiNhanAdapter.ViewHolder>{
    Context context;
    List<NguoiNhanDTO> list;

    public NguoiNhanAdapter(Context context, List<NguoiNhanDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NguoiNhanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nguoi_nhan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiNhanAdapter.ViewHolder holder, int position) {
        NguoiNhanDTO nguoiNhanDTO = list.get(position);
        holder.id.setText(nguoiNhanDTO.getId());
        holder.name.setText(nguoiNhanDTO.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id,name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);

            // Set click listener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle click event
                    GuiThongBaoActivity activity = (GuiThongBaoActivity) v.getContext();
                    activity.setNguoiNhan(id.getText().toString());
                }
            });

        }
    }
}
