package com.nhom7.appqldt.Activitys.GiangVien;

import static com.nhom7.appqldt.R.layout.activity_list_de_tai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Adapters.DeTaiAdapter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Helpers.DangNhapHelper;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Account;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDeTaiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Project> projectList = new ArrayList<>();

    private MutableLiveData<List<Project>> dataList = new MutableLiveData<>();
    public LiveData<List<Project>> getDataList() {
        return dataList;
    }
    EditText tvSearch;
    Button btnSearch;
    TextView tvLabel;
    private  void AnhXa() {
        recyclerView = findViewById(R.id.recycler_view_detais);
        tvLabel = findViewById(R.id.tvLabelListSize);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_list_de_tai);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
//Lấy giá trị được lưu giữ ra
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(sharedPreferences.getString("username",""));


        AnhXa();
        loadRecyclerView();

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }
    public void updateGlobalData(List<Project> dataList) {
        ListDeTaiActivity.this.projectList.clear();
        ListDeTaiActivity.this.projectList.addAll(dataList);
        // Cập nhật UI nếu cần thiết
    }
    private void setData(List<Project> list){
        DeTaiAdapter deTaiAdapter = new DeTaiAdapter(list, ListDeTaiActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListDeTaiActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(deTaiAdapter);
    }
    private void loadRecyclerView() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Project>>> call = apiService.getAllProjectActiveProjectForLecturer();
        call.enqueue(new Callback<APIResponse<List<Project>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Project>>> call, Response<APIResponse<List<Project>>> response) {
                if(response.isSuccessful()){
                    List<Project> projects = response.body().getResult();
                    updateGlobalData(projects);
                    setData(projects);
                    if (projects != null) {
                        dataList.postValue(projects);
                    }
                    if(projects.size()==0){
                        tvLabel.setVisibility(View.VISIBLE);
                        tvLabel.setText("Không có đề tài nào có thể đăng ký");
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Project>>> call, Throwable t) {

            }
        });

    }

    private void search(){
        tvSearch = findViewById(R.id.tvSearch);
        if(tvSearch.getText().length()==0){
           loadRecyclerView();
        }
        else {
            APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
            Call<APIResponse<List<Project>>> call = apiService.getAllSearchProjectActiveProjectForLecturer(tvSearch.getText().toString().trim());
            call.enqueue(new Callback<APIResponse<List<Project>>>() {
                @Override
                public void onResponse(Call<APIResponse<List<Project>>> call, Response<APIResponse<List<Project>>> response) {
                    if(response.isSuccessful()){
                        if(response.body().isSuccess()){
                            List<Project> projects = response.body().getResult();
                            setData(projects);
                            if(projects.size()==0){
                                tvLabel.setVisibility(View.VISIBLE);
                                tvLabel.setText("Không tìm thấy đề tài nào");
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<APIResponse<List<Project>>> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean sItem = MenuHelper.ChonItem(this, item);
        if(sItem){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}