package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Models.ThanhVien;
import com.nhom7.appqldt.Models.ThongBao;
import com.nhom7.appqldt.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {

    List<ThongBao> listTB;
    Context context;

    public ThongBaoAdapter(List<ThongBao> listTB, Context context) {
        this.listTB = listTB;
        this.context = context;
    }


    @NonNull
    @Override
    public ThongBaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_thongbao_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoAdapter.ViewHolder holder, int position) {
        ThongBao thongBao = this.listTB.get(position);
        holder.tv_TB_TieuDe.setText((CharSequence) thongBao.getTieuDe());
        holder.tv_TB_NguoiGui.setText((CharSequence) thongBao.getNguoiGui());
        holder.tv_TB_NgayGui.setText((CharSequence) thongBao.getNgayGui());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, NoiDungThongBaoActivity.class);
//                intent.putExtra("noiDungTB", thongBao.getNoiDung());
//                context.startActivity(intent);
                DialogHelper.showDialog(context, // Context của Activity hiện tại
                        "Nội dung thông báo", // Tiêu đề của dialog
                        thongBao.getNoiDung(), // Nội dung của dialog
                        "OK", // Text của nút Positive
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Xử lý sự kiện khi người dùng nhấn nút Positive
                                dialog.dismiss(); // Đóng dialog sau khi người dùng nhấn nút Positive (tùy chọn)
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTB.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_TB_TieuDe, tv_TB_NguoiGui, tv_TB_NgayGui;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TB_TieuDe = itemView.findViewById(R.id.tv_TB_TieuDe);
            tv_TB_NguoiGui = itemView.findViewById(R.id.tv_TB_NguoiGui);
            tv_TB_NgayGui = itemView.findViewById(R.id.tv_TB_NgayGui);
        }
    }
}

