package com.nhom7.appqldt.ui_qlkh;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nhom7.appqldt.R;
import com.nhom7.appqldt.databinding.ActivityMainQlkhBinding;

public class MainActivityQLKH extends AppCompatActivity {

    private ActivityMainQlkhBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainQlkhBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_quanlydetai,R.id.navigation_pheduyetdetai,R.id.navigation_danhsachchude, R.id.navigation_guithongbao)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main_qlkh);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}