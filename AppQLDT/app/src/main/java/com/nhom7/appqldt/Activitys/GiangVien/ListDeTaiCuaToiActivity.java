package com.nhom7.appqldt.Activitys.GiangVien;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.ProjectDTO;
import com.nhom7.appqldt.Adapters.DeTaiAdapter;
import com.nhom7.appqldt.Adapters.DeTaiDaLamAdapter;
import com.nhom7.appqldt.Helpers.MenuHelper;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDeTaiCuaToiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ProjectDTO> listDeTai;

    String username;
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
        setContentView(R.layout.activity_list_de_tai_cua_toi);

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
        username = sharedPreferences.getString("username","");
        TextView tvUserName = (TextView) findViewById(R.id.toolbar_username);
        tvUserName.setText(username);


        AnhXa();
        loadRecyclerView();

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRecyclerView();
            }
        });
    }

    private void loadRecyclerView() {
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        tvSearch = findViewById(R.id.tvSearch);
        String searchText = tvSearch.getText().toString();
        Call<APIResponse<List<ProjectDTO>>> call = apiService.getAllMyProjectForLecturer(username, searchText);

        call.enqueue(new Callback<APIResponse<List<ProjectDTO>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<ProjectDTO>>> call, Response<APIResponse<List<ProjectDTO>>> response) {
                if(response.isSuccessful()){
                    List<ProjectDTO> projects = response.body().getResult();
                    DeTaiDaLamAdapter deTaiAdapter = new DeTaiDaLamAdapter(projects, ListDeTaiCuaToiActivity.this);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListDeTaiCuaToiActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(deTaiAdapter);
                    if(projects.size()==0){
                        tvLabel.setVisibility(View.VISIBLE);
                        tvLabel.setText("Không có đề tài nào");
                    }else {
                        tvLabel.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<ProjectDTO>>> call, Throwable t) {
                Log.d("API", "fail call");
            }
        });

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