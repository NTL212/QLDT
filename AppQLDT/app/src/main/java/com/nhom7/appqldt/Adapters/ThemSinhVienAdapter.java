package com.nhom7.appqldt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.StudentDTO;
import com.nhom7.appqldt.Helpers.DialogHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Student;
import com.nhom7.appqldt.R;

import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemSinhVienAdapter extends RecyclerView.Adapter<ThemSinhVienAdapter.ViewHolder> {

    List<StudentDTO> studentList;
    Context context;

    String projectCode;
    public ThemSinhVienAdapter(List<StudentDTO> studentList, Context context, String projectCode){
        this.studentList = studentList;
        this.context = context;
        this.projectCode = projectCode;
    }


    @NonNull
    @Override
    public ThemSinhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_sinhvien_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemSinhVienAdapter.ViewHolder holder, int position) {
        StudentDTO student = this.studentList.get(position);
        holder.tvStt.setText((CharSequence)String.valueOf(position));
        holder.tvHoTen.setText((CharSequence)  student.getName());
        holder.tvMSSV.setText((CharSequence) "MSSV: " +  student.getId());
        holder.tvNgaySinh.setText((CharSequence) student.getBirthday());
        holder.tvLop.setText((CharSequence)  "Lớp: "  +student.getClassObj().getName());
        holder.tvKhoa.setText((CharSequence) "Khoa: " +  student.getFalculity().getName());

        holder.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Call<APIResponse<String>> call = apiService.addMemberToProject(projectCode,student.getId());
                call.enqueue(new Callback<APIResponse<String>>() {
                    @Override
                    public void onResponse(Call<APIResponse<String>> call, Response<APIResponse<String>> response) {
                        if(response.body().isSuccess()){
                            DialogHelper.showSuccessDialog(context, response.body().getMessage());
                        }else {
                            DialogHelper.showFailedDialog(context, response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse<String>> call, Throwable t) {
                        DialogHelper.showFailedDialog(context, t.getMessage());
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView tvStt, tvHoTen,  tvMSSV, tvNgaySinh, tvLop, tvKhoa;
        Button btnThem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStt = itemView.findViewById(R.id.tv_TV_STT);
            tvHoTen = itemView.findViewById(R.id.tv_TV_HoTen);
            tvMSSV = itemView.findViewById(R.id.tv_TV_MSSV);
            tvNgaySinh = itemView.findViewById(R.id.tv_TV_NamSinh);
            tvLop = itemView.findViewById(R.id.tv_TV_Lop);
            tvKhoa = itemView.findViewById(R.id.tv_TV_Khoa);
            btnThem = itemView.findViewById(R.id.btnThemTV);
        }
    }
}
