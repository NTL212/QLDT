package com.nhom7.appqldt.Activitys.QuanLy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Adapters.DeTaiCanPheDuyetAdapter;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.DeTai;
import com.nhom7.appqldt.Models.DeTaiCanPheDuyet;
import com.nhom7.appqldt.Models.Project;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class PheDuyetDeTaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DeTaiCanPheDuyet> listDeTai;
    DeTaiCanPheDuyetAdapter deTaiAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phe_duyet_de_tai);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);// Gắn Toolbar vào ActionBar
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // Xử lý sự kiện khi người dùng chọn một mục trong menu
                return onOptionsItemSelected(item);
            }
        });

        listDeTai = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view_detais);
        deTaiAdapter = new DeTaiCanPheDuyetAdapter(this, listDeTai);
        recyclerView.setAdapter(deTaiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        deTaiAdapter.notifyDataSetChanged();
        getData();

        

    }

    void getData() {
        APIService apiService = RetrofitClient.getRetrofitInstance2().create(APIService.class);
        Call<APIResponse<List<DeTaiCanPheDuyet>>> call = apiService.getPendingApproval();
        call.enqueue(new retrofit2.Callback<APIResponse<List<DeTaiCanPheDuyet>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<DeTaiCanPheDuyet>>> call, retrofit2.Response<APIResponse<List<DeTaiCanPheDuyet>>> response) {
                if (response.isSuccessful()) {

                    APIResponse<List<DeTaiCanPheDuyet>> apiResponse = response.body();
                    if (apiResponse != null) {
                        List<DeTaiCanPheDuyet> deTaiCanPheDuyets = apiResponse.getResult();
                        for (DeTaiCanPheDuyet deTaiCanPheDuyet : deTaiCanPheDuyets) {
                            listDeTai.add(deTaiCanPheDuyet);
                            deTaiAdapter.notifyDataSetChanged();

                        }

                    }
                } else {
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<DeTaiCanPheDuyet>>> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_quanly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.action_sendNotification) {
            intent = new Intent(this, GuiThongBaoActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_manageProject) {
            intent = new Intent(this, QuanLyDeTaiActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_projectTopic) {
            intent = new Intent(this, ListChuDeActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}