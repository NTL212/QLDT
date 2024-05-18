package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.Activitys.Admin.ChiTietQuanLyAdminActivity;
import com.nhom7.appqldt.Models.ManagerAccount;
import com.nhom7.appqldt.R;

import java.util.List;

public class QuanLyAdapter extends RecyclerView.Adapter<QuanLyAdapter.ViewHolder> {
    List<ManagerAccount> managerAccountList;
    Context context;

    public QuanLyAdapter(List<ManagerAccount> managerAccountList, Context context) {
        this.managerAccountList = managerAccountList;
        this.context = context;
    }

    public QuanLyAdapter() {
        super();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_quanly_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ManagerAccount managerAccount = this.managerAccountList.get(position);
        holder.tvmaql.setText(managerAccount.getAccount().getUsername());
        holder.tvten.setText("Tên Quản Lý : " + managerAccount.getManagementStaff().getName());
        holder.tvgioitinh.setText("Giới tính : " + managerAccount.getManagementStaff().getSex());
        holder.tvngaysinh.setText("Ngày sinh : " + managerAccount.getManagementStaff().getBirthday());
        holder.tvsdt.setText("Số điện thoại : " +managerAccount.getManagementStaff().getPhoneNum());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("empCode", managerAccount.getManagementStaff().getManagerCode());
                Intent intent = new Intent(context, ChiTietQuanLyAdminActivity.class);
                intent.putExtra("empCode", managerAccount.getManagementStaff().getEmpCode());// Gắn dữ liệu vào Intent
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return managerAccountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvmaql, tvten, tvngaysinh, tvsdt, tvgioitinh;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvmaql = itemView.findViewById(R.id.tv_DSQL_Admin_STT);
            tvten = itemView.findViewById(R.id.tv_DSQL_Admin_HoTen);
            tvngaysinh = itemView.findViewById(R.id.tv_DSQL_Admin_NamSinh);
            tvsdt = itemView.findViewById(R.id.tv_DSQL_Admin_SDT);
            tvgioitinh = itemView.findViewById(R.id.tv_DSQL_Admin_GioiTinh);
        }
    }
}
