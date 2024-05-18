package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.Admin.ChiTietHocSinhAdminActivity;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.R;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder>{
    List<StudentDTO> studentDTOList;

    Context context;

    public StudentAdapter(List<StudentDTO> studentDTOList, Context context) {
        this.studentDTOList = studentDTOList;
        this.context = context;
    }

    public StudentAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_student_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentDTO studentDTO = this.studentDTOList.get(position);
        holder.tvmahs.setText(studentDTO.getId());
        holder.tvten.setText("Họ tên : "+ studentDTO.getName());
        holder.tvngaysinh.setText("Ngày sinh : " + studentDTO.getBirthday());
        holder.tvkhoa.setText("Khoa : " + studentDTO.getFalculity().getName());
        holder.tvlop.setText("Lớp : " + studentDTO.getClassObj().getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("studentId",studentDTO.getId());
                Intent intent = new Intent(context, ChiTietHocSinhAdminActivity.class);
                intent.putExtra("studentId", studentDTO.getId());// Gắn dữ liệu vào Intent
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return studentDTOList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvmahs, tvten, tvngaysinh, tvkhoa, tvlop;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvmahs = itemView.findViewById(R.id.tv_DSHS_Admin_STT);
            tvten = itemView.findViewById(R.id.tv_DSHS_Admin_HoTen);
            tvngaysinh = itemView.findViewById(R.id.tv_DSHS_Admin_NamSinh);
            tvkhoa = itemView.findViewById(R.id.tv_DSHS_Admin_Khoa);
            tvlop = itemView.findViewById(R.id.tv_DSHS_Admin_Lop);
        }
    }
}
