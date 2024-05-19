package com.nhom7.appqldt.Activitys.QuanLy;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Activitys.DTO.NguoiNhanDTO;
import com.nhom7.appqldt.Adapters.NguoiNhanAdapter;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.LecturerAccount;
import com.nhom7.appqldt.Models.ManagerAccount;
import com.nhom7.appqldt.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChonNguoiNhanDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChonNguoiNhanDialog extends DialogFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChonNguoiNhanDialog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChonNguoiNhanDialog.
     */
    // TODO: Rename and change types and number of parameters
    public static ChonNguoiNhanDialog newInstance(String param1, String param2) {
        ChonNguoiNhanDialog fragment = new ChonNguoiNhanDialog();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    List<NguoiNhanDTO> listgv, listql;
    RecyclerView r1,r2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chon_nguoi_nhan_dialog, container, false);
        listgv = new ArrayList<>();
        listql = new ArrayList<>();
        r1 = view.findViewById(R.id.recyclerViewgv);
        r2 = view.findViewById(R.id.recyclerViewql);
        APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
        apiService.getAllLecturer().enqueue(new Callback<APIResponse<List<LecturerAccount>>>() {
            @Override
            public void onResponse(Call<APIResponse<List<LecturerAccount>>> call, Response<APIResponse<List<LecturerAccount>>> response) {
                if (response.body().isSuccess()) {
                    List<LecturerAccount> lecturerAccounts = response.body().getResult();
                    for (LecturerAccount lecturerAccount : lecturerAccounts) {
                        NguoiNhanDTO nguoiNhanDTO = new NguoiNhanDTO();
                        nguoiNhanDTO.setId(lecturerAccount.getAccount().getUsername());
                        nguoiNhanDTO.setName(lecturerAccount.getLecturer().getName());
                        listgv.add(nguoiNhanDTO);

                    }
                    r1.setAdapter(new NguoiNhanAdapter(getContext(), listgv));
                    r1.setVisibility(View.VISIBLE);
                    r1.setHasFixedSize(true);
                    r1.setNestedScrollingEnabled(false);
                    r1.setRecycledViewPool(new RecyclerView.RecycledViewPool());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    r1.setLayoutManager(layoutManager);
                    NguoiNhanAdapter adapter = new NguoiNhanAdapter(getContext(), listgv);
                    r1.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<LecturerAccount>>> call, Throwable t) {

            }
        });
        apiService.getAllManager().enqueue(new Callback<APIResponse<List<ManagerAccount>>> () {
            @Override
            public void onResponse(Call<APIResponse<List<ManagerAccount>>> call, Response<APIResponse<List<ManagerAccount>>> response) {
                if (response.body().isSuccess()) {
                    List<ManagerAccount> managerAccounts = response.body().getResult();
                    for (ManagerAccount managerAccount : managerAccounts) {
                        NguoiNhanDTO nguoiNhanDTO = new NguoiNhanDTO();
                        nguoiNhanDTO.setId(managerAccount.getAccount().getUsername());
                        nguoiNhanDTO.setName(managerAccount.getManagementStaff().getName());
                        listql.add(nguoiNhanDTO);

                    }
                    r2.setAdapter(new NguoiNhanAdapter(getContext(), listql));
                    r2.setVisibility(View.VISIBLE);
                    r2.setHasFixedSize(true);
                    r2.setNestedScrollingEnabled(false);
                    r2.setRecycledViewPool(new RecyclerView.RecycledViewPool());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    r2.setLayoutManager(layoutManager);
                    NguoiNhanAdapter adapter = new NguoiNhanAdapter(getContext(), listql);
                    r2.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<APIResponse<List<ManagerAccount>>> call, Throwable t) {

            }
        });
        return view;
    }
}