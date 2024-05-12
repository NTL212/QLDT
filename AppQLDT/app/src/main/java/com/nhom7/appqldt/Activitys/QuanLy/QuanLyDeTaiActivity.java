package com.nhom7.appqldt.Activitys.QuanLy;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class QuanLyDeTaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Project> listDeTai;
    ImageView btnThemDeTai;
    DeTaiAdapter deTaiAdapter;


    //chuyen activity co nhan du lieu tra ve
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    Project project = (Project) bundle.getSerializable("project");
                    Log.e(TAG, "trabe: " + project.getProjectCode());
                    addDeTai(project);
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_de_tai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });
        anhxa();
        initializeData();
        recyclerView = findViewById(R.id.recycler_view_detais);
        deTaiAdapter = new DeTaiAdapter(this, listDeTai);
        recyclerView.setAdapter(deTaiAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnThemDeTai.setOnClickListener(v -> {
            Intent intent = new Intent(QuanLyDeTaiActivity.this, ThemDeTaiActivity.class);
            mStartForResult.launch(intent);


        });
        //chuyen activity co nhan du lieu tra ve


    }

    void anhxa(){
        btnThemDeTai = findViewById(R.id.btnThemDeTai);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }
    private void initializeData() {
        listDeTai = new ArrayList<>();
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        Call<APIResponse<List<Project>>> call = apiService.getAllProject();
        call.enqueue(new retrofit2.Callback<APIResponse<List<Project>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Project>>> call, retrofit2.Response<APIResponse<List<Project>>> response) {
                if(response.isSuccessful()){
                    List<Project> projects = response.body().getResult();
                    listDeTai.addAll(projects);
                    deTaiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<Project>>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_sendNotification) {
            intent = new Intent(this, GuiThongBaoActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_approveProject) {
            intent = new Intent(this, PheDuyetDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_projectTopic) {
            intent = new Intent(this, ListChuDeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void addDeTai(Project project){
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);

//        Call<APIResponse<Project>> call = apiService.createProject(project);
        Log.e(TAG, "addDeTai: " +project);
//        call.enqueue(new retrofit2.Callback<APIResponse<Project>>() {
//            @Override
//            public void onResponse(Call<APIResponse<Project>> call, retrofit2.Response<APIResponse<Project>> response) {
//                if(response.isSuccessful()){
//                    Log.e(TAG, "onResponse: " + response.body().getResult());
//                    deTaiAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<APIResponse<Project>> call, Throwable t) {
//
//            }
//        });
    }
}