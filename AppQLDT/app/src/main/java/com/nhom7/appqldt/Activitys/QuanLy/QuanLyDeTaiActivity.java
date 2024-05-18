package com.nhom7.appqldt.Activitys.QuanLy;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.nhom7.appqldt.Models.ChuDe;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.Models.Topic;
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
//    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK) {
//                    Intent data = result.getData();
//                    Bundle bundle = data.getExtras();
//                    Project project = (Project) bundle.getSerializable("project");
////                    Log.e(TAG, "trabe: " + project.getProjectCode());
//                    addDeTai(project);
//                }
//                else {
//                    addDeTai(null);
//                }
//            });

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


        btnThemDeTai.setOnClickListener(v -> {
//            Intent intent = new Intent(QuanLyDeTaiActivity.this, ThemDeTaiActivity.class);
//            mStartForResult.launch(intent);
            //show dialog
            ThemDeTaiDiaLog themDeTaiDiaLog = new ThemDeTaiDiaLog();
            themDeTaiDiaLog.show(getSupportFragmentManager(), "ThemDeTaiDiaLog");
            initializeData();

        });
        //chuyen activity co nhan du lieu tra ve


    }

    void anhxa() {
        btnThemDeTai = findViewById(R.id.btnThemDeTai);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }

    private void initializeData() {

        listDeTai = new ArrayList<>();
        Log.e(TAG, "onResponse: " + listDeTai.size());

        APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
        Call<APIResponse<List<Project>>> call = apiService.getAllProjectManager();
        call.enqueue(new retrofit2.Callback<APIResponse<List<Project>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<Project>>> call, retrofit2.Response<APIResponse<List<Project>>> response) {
                Log.e(TAG, "onResponse: " + response.body());
                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse: " + response.body().getResult());
                    List<Project> projects = response.body().getResult();
                    listDeTai.addAll(projects);
                    Log.e(TAG, "onResponse: " + listDeTai.size());
                    deTaiAdapter = new DeTaiAdapter(QuanLyDeTaiActivity.this, listDeTai);
                    recyclerView.setAdapter(deTaiAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuanLyDeTaiActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    SpaceItemDecoration dividerItemDecoration = new SpaceItemDecoration(20);
                    recyclerView.addItemDecoration(dividerItemDecoration);

//                    set margin
                    deTaiAdapter.notifyDataSetChanged();
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
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

    void addDeTai(Project project) {
        APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);

        Project project2 = new Project();
        project2=project;
        Call<APIResponse<Project>> call = apiService.createProject(project2);
//        Log.e(TAG, "addDeTai: " + project2.getProjectCode());
        call.enqueue(new retrofit2.Callback<APIResponse<Project>>() {
            @Override
            public void onResponse(Call<APIResponse<Project>> call, retrofit2.Response<APIResponse<Project>> response) {
                if (response.isSuccessful()) {
                    if (response.body().isSuccess()) {
//                        Log.e(TAG, "onResponse: " + response.body().getStatusCode());
//                        Log.e(TAG, "onResponse: " + response.body().getMessage());
//                        Log.e(TAG, "onResponse: " + response.body().isSuccess());
//                        Log.e(TAG, "onResponse: " + response.body().getResult());
//                        Log.e(TAG, "onResponse: " + response.body().toString());
                        Project project = response.body().getResult();
                        listDeTai.add(project);
                        deTaiAdapter.notifyDataSetChanged();
                        Toast.makeText(QuanLyDeTaiActivity.this, "Thêm đề tài thành công", Toast.LENGTH_SHORT).show();
//                        Log.e("o day", "o day" + project);
//                        Log.e(TAG, "onResponse: " + project.getProjectCode());
                    }
                    else {
                        Toast.makeText(QuanLyDeTaiActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<APIResponse<Project>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }

        });
    }
}