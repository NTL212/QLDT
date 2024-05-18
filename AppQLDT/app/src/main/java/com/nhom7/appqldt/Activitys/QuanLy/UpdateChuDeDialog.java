package com.nhom7.appqldt.Activitys.QuanLy;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.nhom7.appqldt.API.APIService;
import com.nhom7.appqldt.API.RetrofitClient;
import com.nhom7.appqldt.Models.APIResponse;
import com.nhom7.appqldt.Models.Topic;
import com.nhom7.appqldt.R;


public class UpdateChuDeDialog extends DialogFragment {


    public UpdateChuDeDialog() {
        // Required empty public constructor
    }

    Topic chuDe;

    @SuppressLint("ValidFragment")
    public UpdateChuDeDialog(Topic chuDe) {
        // Required empty public constructor
        super();
        this.chuDe = chuDe;
    }


    public static UpdateChuDeDialog newInstance(String param1, String param2) {
        UpdateChuDeDialog fragment = new UpdateChuDeDialog();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private EditText editTextTopicCode;
    private EditText editTextName;
    private Switch switchIsEnabled;
    private Button buttonUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_chu_de_dialog, container, false);
        editTextTopicCode = view.findViewById(R.id.editTextTopicCode);
        editTextTopicCode.setText(chuDe.getTopicCode());
        editTextName = view.findViewById(R.id.editTextName);
        editTextName.setText(chuDe.getName());
        switchIsEnabled = view.findViewById(R.id.switchIsEnabled);
        switchIsEnabled.setChecked(chuDe.isEnabled());
        buttonUpdate = view.findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topicCode = editTextTopicCode.getText().toString();
                String name = editTextName.getText().toString();
                boolean isEnabled = switchIsEnabled.isChecked();
                Topic chuDe = new Topic(topicCode, name, isEnabled);
                Log.e("dacl", topicCode + " " + name + " " + isEnabled);
                APIService apiService = RetrofitClient.getRetrofitInstance().create(APIService.class);
                Log.e("dacl", "daclick");
                apiService.updateTopic(chuDe).enqueue(new retrofit2.Callback<APIResponse<Topic>>() {
                    @Override
                    public void onResponse(retrofit2.Call<APIResponse<Topic>> call, retrofit2.Response<APIResponse<Topic>> response) {
//                        Log.e("",response.body().toString());
                        Log.e("",response.toString());

                        if (response.body() != null) {
                            if (response.body().isSuccess()) {
                                ListChuDeActivity listChuDeActivity = (ListChuDeActivity) getActivity();
                                listChuDeActivity.editCD(chuDe);
                                dismiss();
                            }
                        } else
                            Log.e("dac", "daclickloi");

                    }

                    @Override
                    public void onFailure(retrofit2.Call<APIResponse<Topic>> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }

        });
        return view;
    }
}